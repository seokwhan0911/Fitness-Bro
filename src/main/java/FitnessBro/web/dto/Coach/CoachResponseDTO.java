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

        String name;
        int age;
        float rating;
        String comment;     // 한줄 인사말 ex) 운동 3년차, 체지방률 14%
        String introduction;    // 코치 소개
        int price;          // 시급 가격
        String address;     // 위치
        String schedule;
        MultipartFile coachPicture;

    }

    @Getter
    @Builder
    public static class CoachMyPageDTO{

        private String nickname;
        private Long matchNum;
        private Long reviewNum;
        //private String image;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoachUpdateResponseDTO{
        private String nickname;
        private String email;
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
    }


}
