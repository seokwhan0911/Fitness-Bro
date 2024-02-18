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
                        .address(gym.getAddress())
                        .region(gym.getRegion())
                        .subAddress(gym.getSubAddress())
                        .detailAddress(gym.getDetailAddress())
                        .postal_code(gym.getPostalCode())
                        .build()).collect(Collectors.toList());
    }



}
