package FitnessBro.web.dto.Coach;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CoachRequestDTO {

    @Getter
    @Builder
    public static class CoachProfileRegisterDTO{

        String nickname;    //닉네임
        String introduction;    //선생님 소개
        String schedule;    // 주 운동 시간
        String comment;     // 한줄 인사말 ex) 운동 3년차, 체지방률 14%
        int price;          // 시급 가격
        
    }

}
