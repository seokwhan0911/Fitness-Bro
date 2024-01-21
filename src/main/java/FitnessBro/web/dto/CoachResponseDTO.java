package FitnessBro.web.dto;


import lombok.Builder;
import lombok.Getter;


public class CoachResponseDTO {

    @Getter
    @Builder
    public static class CoachProfileDTO{

        private String name;
        private int age;
        private float rating;
        private String comment;
        private String introduction;
        private int price;
        private String address;
        private String schedule;

    }

}
