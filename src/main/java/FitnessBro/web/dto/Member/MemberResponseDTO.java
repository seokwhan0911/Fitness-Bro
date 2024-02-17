package FitnessBro.web.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class MemberMyPageDTO{
        String nickname;
        Long matchNum;
        Long reviewNum;
        String memberImage;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberUpdateResponseDTO{
        private String nickname;
        private String email;
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberMyInfoDTO {
        String nickname;    // 회원 닉네임
        String address;     // 회원 거주 지역
        String memberImage;
    }
}
