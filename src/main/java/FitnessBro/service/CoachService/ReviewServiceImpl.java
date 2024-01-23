package FitnessBro.service.CoachService;


import FitnessBro.domain.review.Entity.Review;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.respository.ReviewRepository;
import FitnessBro.web.dto.ReviewsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;


    public List<ReviewsResponseDTO.ReviewDTO> getReviews(Long coachId){
        List<Review> reviews = reviewRepository.findAllByCoachId(coachId);
        return reviews.stream()
                .map(ReviewsResponseDTO.ReviewDTO::from)
                .collect(Collectors.toList());
    }



}
