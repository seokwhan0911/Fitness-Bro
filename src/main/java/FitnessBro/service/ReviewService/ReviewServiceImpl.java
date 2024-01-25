package FitnessBro.service.ReviewService;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.MemberRepository;
import FitnessBro.respository.ReviewRepository;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private  final MemberRepository memberRepository;
    private final CoachRepository coachRepository;

    public List<Review> getByCoachId(Long coachId) {
        List<Review> result = reviewRepository.findByCoachId(coachId);
        return result;
    }

    public List<ReviewResponseDTO.ReviewByUserDTO> getReviews(Long userId) {
        List<Review> reviews = reviewRepository.findAllByMemberId(userId);
        return reviews.stream()
                .map(ReviewResponseDTO.ReviewByUserDTO::from)
                .collect(Collectors.toList());

    }
    public ReviewResponseDTO.ReviewByUserDTO createReview(ReviewRequestDTO.CreateReviewDTO createReviewDTO, Long userId){
        Member member = memberRepository.getById(userId);
        Coach coach = coachRepository.findByNickname(createReviewDTO.getCoachName());

        Review review = ReviewRequestDTO.CreateReviewDTO.toEntity(createReviewDTO,member,coach);

        reviewRepository.save(review);

        return ReviewResponseDTO.ReviewByUserDTO.from(review);


    }

}