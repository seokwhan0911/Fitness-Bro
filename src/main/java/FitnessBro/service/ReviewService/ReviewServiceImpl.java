package FitnessBro.service.ReviewService;

import FitnessBro.domain.review.Entity.Review;
import FitnessBro.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    public List<Review> getByCoachId(Long coachId) {
        List<Review> result = reviewRepository.findByCoachId(coachId);
        return result;
    }

}