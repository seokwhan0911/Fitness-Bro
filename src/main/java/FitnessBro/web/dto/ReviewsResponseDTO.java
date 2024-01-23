package FitnessBro.web.dto;

import FitnessBro.domain.review.Entity.Review;
import FitnessBro.service.CoachService.ReviewServiceImpl;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ReviewsResponseDTO {

    @Getter
    @Builder
    public static class CoachReveiwsResponseDTO{

        private String coachName;
        private int coachAge;
        private float coachRating;
        private String comment;
        private List<ReviewDTO> reviews;


    }

    @Getter
    @Builder
    public static class ReviewDTO{
        private String userNickname;
        private LocalDateTime date;
        private String content;

        public static ReviewDTO from(Review review){
            return ReviewDTO.builder()
                    .userNickname(review.getMember().getNickname())
                    .content(review.getContents())
                    .date(review.getDate())
                    .build();
        }
    }




}
