package FitnessBro.converter;

import FitnessBro.domain.Gym;
import FitnessBro.web.dto.Gym.GymResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class GymConverter {

    public static List<GymResponseDTO.GymListDTO> toGymListDTOS(List<Gym> gyms){
        return gyms.stream()
                .map(gym -> GymResponseDTO.GymListDTO.builder()
                        .id(gym.getId())
                        .name(gym.getName())
                        .region(gym.getRegion())
                        .subAddress(gym.getSub_address())
                        .detailAddress(gym.getDetail_address())
                        .postal_code(gym.getPostal_code())
                        .build()).collect(Collectors.toList());
    }



}
