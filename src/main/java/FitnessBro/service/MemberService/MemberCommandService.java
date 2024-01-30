package FitnessBro.service.MemberService;

import FitnessBro.domain.member.Entity.Member;
import FitnessBro.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    Member joinUser(MemberRequestDTO.JoinDTO request);
}
