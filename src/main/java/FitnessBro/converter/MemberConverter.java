package FitnessBro.converter;

import FitnessBro.domain.member.Entity.Member;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import FitnessBro.web.dto.Member.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {


    public static Member toMember(MemberRequestDTO.JoinDTO request){
        return Member.builder()
                .password(request.getPassword())
                .nickname(request.getNickname())
                .age(request.getAge())
                .email(request.getEmail())
                .build();
    }
}
