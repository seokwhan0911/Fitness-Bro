package FitnessBro.converter;

import FitnessBro.domain.Member;
import FitnessBro.web.dto.Member.MemberRequestDTO;

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
