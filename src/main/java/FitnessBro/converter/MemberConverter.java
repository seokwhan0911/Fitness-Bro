package FitnessBro.converter;

import FitnessBro.domain.member.Entity.Member;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import FitnessBro.web.dto.Member.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request){
        return Member.builder()
                .nickname(request.getNickname())
                .id(request.getMemberId())
                .age(request.getAge())
                .email(request.getEmail())
                .build();
    }

    public static MemberResponseDTO.MemberMyPageDTO toMemberMyPageDTO(Member member, Long matchNum, Long reviewNum) {
        return MemberResponseDTO.MemberMyPageDTO.builder()
                .nickname(member.getNickname())
                .matchNum(matchNum)
                .reviewNum(reviewNum)
                .build();
    }
}
