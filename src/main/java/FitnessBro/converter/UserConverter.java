package FitnessBro.converter;

import FitnessBro.domain.user.Entity.User;
import FitnessBro.web.dto.UserRequestDTO;
import FitnessBro.web.dto.UserResponseDTO;

import java.time.LocalDateTime;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDTO request){
        return User.builder()
                .nickname(request.getNickname())
                .id(request.getUserId())
                .age(request.getAge())
                .build();
    }
}
