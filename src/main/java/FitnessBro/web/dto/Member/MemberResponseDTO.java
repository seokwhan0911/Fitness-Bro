package FitnessBro.web.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long email;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class MemberMyPageDTO{
        private String nickname;
        private Long matchNum;
        private Long reviewNum;
        //private String image;
    }

}
