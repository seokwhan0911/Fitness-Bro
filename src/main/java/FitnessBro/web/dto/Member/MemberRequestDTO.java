package FitnessBro.web.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDTO {

    @Builder
    @Getter
    public static class JoinDTO{
        String password;
        String nickname;
        String email;
    }

    @Builder
    @Getter
    public static class loginDTO{
        String email;
        String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberProfileRegisterDTO{

        String nickname;    // 회원 닉네임
        String address;     // 회원 거주 지역

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberUpdateRequestDTO{
        private String nickname;
        private String email;
        private String password;
    }
}
