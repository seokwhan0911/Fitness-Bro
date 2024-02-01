package FitnessBro.converter;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.web.dto.ReviewRequestDTO;
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
                                        .nickname(review.getMember().getNickname())
                                        .contents(review.getContents())
                                        .date(review.getDate())
                                        .build())
                .collect(Collectors.toList());
    }

    public static Review toEntity(ReviewRequestDTO.CreateReviewDTO createReviewDTO, Member member, Coach coach){
        return Review.builder()
                .coach(coach)
                .member(member)
                .contents(createReviewDTO.getContents())
                .date(createReviewDTO.getCreatedAt())
                .rating(createReviewDTO.getRating())
                .build();
    }


}