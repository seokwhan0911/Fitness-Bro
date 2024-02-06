package FitnessBro.service.MemberService;


import FitnessBro.domain.Member;
import FitnessBro.web.dto.Member.MemberRequestDTO;

public interface MemberCommandService {
     Member getMemberById(Long memberId);

    String joinMember(MemberRequestDTO.JoinDTO request);

    String login(String email, String password);

    public String joinSocialMember(String email, String id);
}
