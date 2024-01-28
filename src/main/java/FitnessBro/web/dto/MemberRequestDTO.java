package FitnessBro.web.dto;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO{
        String userId;
        String password;
        String nickname;
        String email;
        int age;
    }
}
