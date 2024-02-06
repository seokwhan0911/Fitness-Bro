package FitnessBro.converter;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.domain.Review;
import FitnessBro.domain.ReviewImage;
import FitnessBro.web.dto.ReviewRequestDTO;
import FitnessBro.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static List<ReviewResponseDTO.ReviewByCoachDTO> toReviewByCoachDTO(List<Review> reviews){
        return reviews.stream()
                .map(review -> ReviewResponseDTO.ReviewByCoachDTO.builder()
                        .nickname(review.getMember().getNickname())
                        .contents(review.getContents())
                        .build()).collect(Collectors.toList());
    }

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO createReviewDTO, Member member, Coach coach){
        return Review.builder()
                .coach(coach)
                .member(member)
                .contents(createReviewDTO.getContents())
                .rating(createReviewDTO.getRating())
                .build();
    }

    public static ReviewImage toReviewImage(String pictureUrl, Review review) {
        return ReviewImage.builder()
                .url(pictureUrl)
                .review(review)
                .build();
    }
}