package FitnessBro.web.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Getter
    @Builder
    public static class ReviewByCoachDTO{

        private String nickname;
        private LocalDateTime date;
        private String contents;




    }

    @Getter
    @Builder
    public static class ReviewsByUserDTO{

        private String coachName;

        private LocalDateTime createdAt;

        private String contents;

        private float rating;

    }

}