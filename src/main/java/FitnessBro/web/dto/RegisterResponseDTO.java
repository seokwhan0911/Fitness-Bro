package FitnessBro.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class RegisterResponseDTO {

    @Builder
    @Getter
    public static class RegisterMemberDTO{
        String nickname;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class RegisterCoachDTO{
        String nickname;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class RequestRegisterDTO{
        String memberNickname;
    }
}
