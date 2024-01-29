package FitnessBro.converter;

import FitnessBro.domain.user.Entity.Member;
import FitnessBro.web.dto.MemberRequestDTO;
import FitnessBro.web.dto.UserResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(Member user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request){
        return Member.builder()
                .memberId(request.getMemberId())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .age(request.getAge())
                .email(request.getEmail())
                .build();
    }
}
