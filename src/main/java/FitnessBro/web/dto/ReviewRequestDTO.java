package FitnessBro.web.dto;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.domain.member.Entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.Optional;


@Data
public class ReviewRequestDTO {


    @Getter
    @NoArgsConstructor
    public static class CreateReviewDTO{


        @NotNull
        private String coachNickname;
        @NotNull
        @Range(min = 0, max = 5)
        private Long rating;
        private String contents;
        @NotNull
        private LocalDateTime createdAt;



    }



}
