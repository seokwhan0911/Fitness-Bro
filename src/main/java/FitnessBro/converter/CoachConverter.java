package FitnessBro.converter;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.web.dto.CoachResponseDTO;

public class CoachConverter {

    public static CoachResponseDTO.CoachProfileDTO toCoachProfileDTO(Coach coach){
        return CoachResponseDTO.CoachProfileDTO.builder()
                .name(coach.getName())
                .age(coach.getAge())
                .rating(coach.getRating())
                .comment(coach.getComment())
                .introduction(coach.getIntroduction())
                .price(coach.getPrice())
                .address(coach.getAddress())
                .schedule(coach.getSchedule())
                .build();
    }
}
