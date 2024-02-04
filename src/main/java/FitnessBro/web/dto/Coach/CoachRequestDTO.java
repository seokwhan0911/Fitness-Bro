package FitnessBro.web.dto.Coach;

import lombok.Builder;
import lombok.Getter;

public class CoachRequestDTO {
    @Getter
    @Builder
    public static class CoachUpdateRequestDTO{
        private String name;
        private String nickname;
        private String email;
        private String password;
        private int age;
        private float rating;
        private String address;
        private String comment;
        private int price;
        private String schedule;
        private String introduction;
    }
}
