package FitnessBro.converter;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.domain.Review;
import FitnessBro.domain.ReviewImage;
import FitnessBro.web.dto.review.ReviewRequestDTO;
import FitnessBro.web.dto.review.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static List<ReviewResponseDTO.ReviewByCoachDTO> toReviewByCoachDTO(List<Review> reviews){    // 동네형이 받은 리뷰 리스트 조회
        return reviews.stream()
                .map(review -> ReviewResponseDTO.ReviewByCoachDTO.builder()
                        .review_id(review.getId())
                        .nickname(review.getMember().getNickname()) // 회원 닉네임
                        .createdAt(review.getCreatedAt())
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

    public static ReviewResponseDTO.ReviewByUserDTO toReviewByUserDTO(Review review){

        return ReviewResponseDTO.ReviewByUserDTO.builder()
                .review_id(review.getId())
                .nickname(review.getCoach().getNickname())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewDetailDTO toReviewDetailDTO(Review review) {
        return ReviewResponseDTO.ReviewDetailDTO.builder()
                .review_id(review.getId())
                .rating(review.getRating())
                .nickname(review.getMember().getNickname())
                .content(review.getContents())
                .pictureURLs(review.getReviewImageList().
                        stream().
                        map(ReviewImage::getUrl).
                        collect(Collectors.toList()))
                .build();
    }
}