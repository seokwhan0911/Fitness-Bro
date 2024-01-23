package FitnessBro.converter;

import FitnessBro.domain.user.Entity.Member;
import FitnessBro.web.dto.UserRequestDTO;
import FitnessBro.web.dto.UserResponseDTO;

import java.time.LocalDateTime;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(Member user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toUser(UserRequestDTO.JoinDTO request){
        return Member.builder()
                .nickname(request.getNickname())
                .age(request.getAge())
                .email(request.getEmail())
                .build();
    }
}
