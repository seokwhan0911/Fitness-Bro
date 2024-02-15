package FitnessBro.web.dto.Coach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CoachRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoachProfileRegisterDTO{

        String nickname;    //닉네임
        Integer age;
        String introduction;    //선생님 소개
        String schedule;    // 주 운동 시간
        String comment;     // 한줄 인사말 ex) 운동 3년차, 체지방률 14%
        String address;
        String region;
        String subAddress;
        String detailAddress;
        int price;          // 시급 가격
        
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoachUpdateRequestDTO{
        private String nickname;
        private String email;
        private String password;
        private String address;
        private String comment;
        private int price;
        private String schedule;
        private String introduction;
    }

}
