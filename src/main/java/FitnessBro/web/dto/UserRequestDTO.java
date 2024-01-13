package FitnessBro.web.dto;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{    // 회원가입 DTO
        String nickname;
        Long userId;
        Integer age;
    }
}
