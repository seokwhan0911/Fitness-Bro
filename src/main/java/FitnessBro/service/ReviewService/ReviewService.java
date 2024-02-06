package FitnessBro.service.ReviewService;

import FitnessBro.domain.Review;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {

    public List<Review> getByCoachId(Long coachId);

    public List<ReviewResponseDTO.ReviewByUserDTO> getReviews(Long userId);

    void createReviewWithFiles(ReviewRequestDTO.CreateReviewDTO createReviewDTO, List<MultipartFile> files, Long userId);

    void createReview(ReviewRequestDTO.CreateReviewDTO request, Long userId);

    public Long getReviewNumCoach(Long coachId);

    public Long getReviewNumMember(Long memberId);

}