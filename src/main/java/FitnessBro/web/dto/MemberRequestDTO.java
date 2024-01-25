package FitnessBro.web.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    @Builder
    public static class JoinDTO {    // 회원가입 DTO
        String nickname;
        Long userId;
        Integer age;
        String email;
    }

}
