package FitnessBro.web.dto.Member;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDTO {
    @Builder
    @Getter
    public static class JoinDTO{
        String password;
        String nickname;
        String email;
        int age;
    }

    @Builder
    @Getter
    public static class loginDTO{
        String email;
        String password;
    }

    @Getter
    @Builder
    public static class MemberProfileRegisterDTO{

        String nickname;    //닉네임
        int age; // 나이

    }

}
