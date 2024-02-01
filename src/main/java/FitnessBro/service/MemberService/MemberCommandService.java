package FitnessBro.service.MemberService;


import FitnessBro.domain.member.Entity.Member;
import FitnessBro.web.dto.Member.MemberRequestDTO;

public interface MemberCommandService {
     Member getMemberById(Long memberId);

}
