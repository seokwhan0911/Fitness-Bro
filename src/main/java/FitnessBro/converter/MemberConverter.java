package FitnessBro.converter;

import FitnessBro.domain.member.Entity.Member;
import FitnessBro.web.dto.MemberRequestDTO;
import FitnessBro.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {

<<<<<<< HEAD
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member user){
        return MemberResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
=======
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
>>>>>>> dev
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
