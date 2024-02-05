package FitnessBro.web.dto.Coach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CoachRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoachUpdateRequestDTO{
        private String nickname;
        private String email;
        private String password;
        private String address;
        private String comment;
        private int price;
        private String schedule;
        private String introduction;
    }
}
