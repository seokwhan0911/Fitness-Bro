package FitnessBro.web.dto.Coach;

import lombok.Builder;
import lombok.Getter;

public class CoachRequestDTO {
    @Getter
    @Builder
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
