package FitnessBro.web.dto;


import FitnessBro.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Getter
    @Builder
    public static class ReviewByCoachDTO{

        private String nickname;
        private LocalDateTime date;
        private String contents;

    }

    @Getter
    @Builder
    public static class ReviewByUserDTO{

        private String coachName;

        private LocalDateTime createdAt;

        private String contents;

        private float rating;


        public static ReviewByUserDTO from(Review review){
            return ReviewByUserDTO.builder()
                    .coachName(review.getCoach().getName())
                    .contents(review.getContents())
                    .rating(review.getCoach().getRating())
                    .createdAt(review.getCreatedAt())
                    .build();
        }

    }

}