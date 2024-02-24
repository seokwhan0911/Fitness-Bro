package FitnessBro.service.ReviewService;

import FitnessBro.domain.Review;
import FitnessBro.web.dto.review.ReviewRequestDTO;
import FitnessBro.web.dto.review.ReviewResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {

    public List<Review> getReviewsByCoachId(Long coachId);

    public List<ReviewResponseDTO.ReviewByUserDTO> getReviews(Long userId);

    void createReviewWithFiles(ReviewRequestDTO.CreateReviewDTO createReviewDTO, List<MultipartFile> files, Long userId);

    void createReview(ReviewRequestDTO.CreateReviewDTO request, Long userId);

    Review getReviewById(Long reviewId);

    public Long getReviewNumCoach(Long coachId);

    public Long getReviewNumMember(Long memberId);

}