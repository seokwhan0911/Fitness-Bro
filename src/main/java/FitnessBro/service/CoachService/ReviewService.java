package FitnessBro.service.CoachService;

import FitnessBro.domain.review.Entity.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> getReviews(Long coachId);
}
