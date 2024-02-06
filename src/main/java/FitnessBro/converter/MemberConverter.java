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

    public static MemberResponseDTO.MemberMyPageDTO toMemberMyPageDTO(Member member, Long matchNum, Long reviewNum) {
        return MemberResponseDTO.MemberMyPageDTO.builder()
                .nickname(member.getNickname())
                .matchNum(matchNum)
                .reviewNum(reviewNum)
                .build();
    }

    public static MemberResponseDTO.MemberUpdateResponseDTO toMemberUpdateDTO(Member member) {
        return MemberResponseDTO.MemberUpdateResponseDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
