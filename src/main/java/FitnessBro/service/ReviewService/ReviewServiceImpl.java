package FitnessBro.service.ReviewService;

import FitnessBro.aws.s3.AmazonS3Manager;
import FitnessBro.converter.ReviewConverter;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Review;
import FitnessBro.domain.Member;
import FitnessBro.domain.common.Uuid;
import FitnessBro.respository.*;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private  final MemberRepository memberRepository;
    private final CoachRepository coachRepository;

    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;


    @Override
    @Transactional
    public List<Review> getByCoachId(Long coachId) {

        List<Review> result = reviewRepository.findByCoachId(coachId);

        return result;
    }

    @Override
    @Transactional
    public List<ReviewResponseDTO.ReviewByUserDTO> getReviews(Long userId) {
        List<Review> reviews = reviewRepository.findAllByMemberId(userId);
        return reviews.stream()
                .map(ReviewResponseDTO.ReviewByUserDTO::from)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void createReviewWithFiles(ReviewRequestDTO.CreateReviewDTO request, List<MultipartFile> files, Long userId){    // 이미지 업로드 기능 추가

        Member member = memberRepository.getById(userId);
        Coach coach = coachRepository.getCoachByNickname(request.getCoachNickname());
        Review review = ReviewConverter.toReview(request, member, coach);

        // file마다 유일한 URL 값 생성
        for(MultipartFile file : files){
            String uuid = UUID.randomUUID().toString();
            Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());

            String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), file);

            reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
        }

        reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void createReview(ReviewRequestDTO.CreateReviewDTO request, Long userId) {

        Member member = memberRepository.getById(userId);
        Coach coach = coachRepository.getCoachByNickname(request.getCoachNickname());

        Review review = ReviewConverter.toReview(request, member, coach);
        reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Long getReviewNumCoach(Long coachId){
        return reviewRepository.countByCoachId(coachId);
    }

    @Override
    @Transactional
    public Long getReviewNumMember(Long memberId){
        return reviewRepository.countByMemberId(memberId);
    }
}