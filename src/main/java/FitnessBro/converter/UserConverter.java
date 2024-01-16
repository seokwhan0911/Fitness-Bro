package FitnessBro.converter;

import FitnessBro.domain.user.Entity.Users;
import FitnessBro.web.dto.UserRequestDTO;
import FitnessBro.web.dto.UserResponseDTO;

import java.time.LocalDateTime;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(Users user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Users toUser(UserRequestDTO.JoinDTO request){
        return Users.builder()
                .nickname(request.getNickname())
                .age(request.getAge())
                .email(request.getEmail())
                .build();
    }
}
