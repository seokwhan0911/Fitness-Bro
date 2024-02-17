package FitnessBro.web.dto.review;


import FitnessBro.domain.Review;
import FitnessBro.domain.ReviewImage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Getter
    @Builder
    public static class ReviewByCoachDTO{   // 동네형이 받은 리스트를 보여주기 위한 DTO

        Long review_id;
        String nickname;    // 유저 닉네임
        LocalDateTime createdAt;    // 후기를 작성한 시간
        String pictureURL;  // 유저 사진

    }

    @Getter
    @Builder
    public static class ReviewByUserDTO{    // 회원이 작성한 후기 리스트를 보여주기 위한 DTO

        Long review_id;
        String nickname;    // 동네형 닉네임
        String coachImage;
        LocalDateTime createdAt;     // 후기를 작성한 시간

    }

    @Getter
    @Builder
    public static class ReviewDetailDTO {      // 리뷰 상세보기 DTO

        private Long review_id;
        private String nickname;    // 회원 닉네임
        private String content;    // 후기 내용
        private Float rating;
        private LocalDateTime createdAt;
        private List<String> pictureURLs;    // 후기에 사용된 이미지 리스트
    }
}