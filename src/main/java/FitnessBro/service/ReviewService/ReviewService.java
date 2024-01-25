package FitnessBro.service.ReviewService;

import FitnessBro.domain.review.Entity.Review;
import FitnessBro.web.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    public List<Review> getByCoachId(Long coachId);

    public List<ReviewResponseDTO.ReviewByUserDTO> getReviews(Long userId);


}