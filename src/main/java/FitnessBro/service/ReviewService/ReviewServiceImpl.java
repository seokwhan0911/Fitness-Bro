package FitnessBro.service.ReviewService;

import FitnessBro.domain.review.Entity.Review;
import FitnessBro.respository.ReviewRepository;
import FitnessBro.web.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

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

}