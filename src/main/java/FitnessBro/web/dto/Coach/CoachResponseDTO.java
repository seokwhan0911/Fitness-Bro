package FitnessBro.web.dto.Coach;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class CoachResponseDTO {

    @Getter
    @Builder
    public static class CoachProfileDTO{

        Long coachId;
        String nickname;
        Integer age;
        float rating;
        String comment;     // 한줄 인사말 ex) 운동 3년차, 체지방률 14%
        String introduction;    // 코치 소개
        Integer price;          // 시급 가격
        String address;     // 위치
        String schedule;
        String coachPicture;
        // 리뷰 수 추가해야 됨

    }

    @Getter
    @Builder
    public static class CoachListDTO{
        Long coachId;
        String nickname;
        String region;
        String subAddress;
        String detailAddress;
        int age;
        float rating;
    }

    @Getter
    @Builder
    public static class CoachMyPageDTO{

        String nickname;
        Long matchNum;
        Long reviewNum;
        String coachImage;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoachUpdateResponseDTO{
        private String nickname;
        private String password;
        private String address;
        private String comment;
        private int price;
        private String schedule;
        private String introduction;
    }

    @Getter
    @Builder
    public static class CoachDTO{
        private Long coachId;
        private String name;
        private int age;
        private float rating;
        private String comment;
        private int price;
        private String address;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class favoriteCoachDTO{
        Long coachId;
        String nickname;
        String address;
        Float rating;
        String coachImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoachAlbumDTO {

        Long coachId;
        List<String> pictureURLs;    // 동네형 사진첩 이미지 리스트
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoachMyInfoDTO {

        String coachPicture;
        String nickname;
        Integer age;
        String schedule;
        Integer price;
        String comment;
        String introduction;
        List<String> pictureURLs;

    }
}
