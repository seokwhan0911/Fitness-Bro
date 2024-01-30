package FitnessBro.web.dto;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.domain.member.Entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

public class ReviewRequestDTO {


    @Getter
    @Builder
    public static class CreateReviewDTO{

        private String coachName;
        private Long rating;
        private String contents;
        private LocalDateTime createdAt;

        public static Review toEntity(CreateReviewDTO createReviewDTO, Member member, Coach coach){
            return Review.builder()
                    .coach(coach)
                    .member(member)
                    .contents(createReviewDTO.contents)
                    .date(createReviewDTO.createdAt)
                    .rating(createReviewDTO.rating)
                    .build();
        }

    }



}
