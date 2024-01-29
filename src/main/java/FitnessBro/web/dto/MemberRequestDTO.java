package FitnessBro.web.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDTO {
    @Builder
    @Getter
    public static class JoinDTO{
        String memberId;
        String password;
        String nickname;
        String email;
        int age;
    }

    @Builder
    @Getter
    public static class loginDTO{
        String memberId;
        String password;
    }

}
