package FitnessBro.web.dto.User;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{
        String nickname;
        Integer age;
        String email;
    }
}
