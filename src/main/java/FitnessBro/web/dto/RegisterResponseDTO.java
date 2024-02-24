package FitnessBro.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class RegisterResponseDTO {

    @Builder
    @Getter
    public static class RegisterMemberDTO{      // 코치와 성사된 유저 리스트
        Long memberId;
        String nickname;
        String memberPicture;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class RegisterCoachDTO{   // 유저와 성사된 코치 리스트
        Long coachId;
        String nickname;
        String coachPicture;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class RequestRegisterDTO{

        Long memberId;
        String memberNickname;
    }
}
