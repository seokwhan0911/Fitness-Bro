package FitnessBro.converter;

import FitnessBro.domain.review.Entity.Review;
import FitnessBro.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static List<ReviewResponseDTO.ReviewByCoachDTO> toReviewByCoachDTO(List<Review> reviews){
        return reviews
                .stream()
                .map(
                        review ->
                                ReviewResponseDTO.ReviewByCoachDTO.builder()
                                        .nickname(review.getUser().getNickname())
                                        .contents(review.getContents())
                                        .date(review.getDate())
                                        .build())
                .collect(Collectors.toList());
    }

}