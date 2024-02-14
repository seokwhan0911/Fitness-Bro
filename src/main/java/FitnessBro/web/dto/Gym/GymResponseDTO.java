package FitnessBro.web.dto.Gym;

import lombok.Builder;
import lombok.Getter;

public class GymResponseDTO {


    @Getter
    @Builder
    public static class GymListDTO{
        Long id;
        String name;
        String region;
        String subAddress;
        String detailAddress;
        String postal_code;

    }
}
