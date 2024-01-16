package FitnessBro.web.dto;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{
        String nickname;
        Integer age;
        String email;
    }
}
