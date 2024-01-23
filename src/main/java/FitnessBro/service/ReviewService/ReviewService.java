package FitnessBro.service.ReviewService;

import FitnessBro.domain.review.Entity.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> getByCoachId(Long coachId);


}
