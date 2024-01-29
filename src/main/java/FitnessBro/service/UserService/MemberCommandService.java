package FitnessBro.service.UserService;

import FitnessBro.domain.user.Entity.Member;
import FitnessBro.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    String joinMember(MemberRequestDTO.JoinDTO request);

    String login(String memberId, String password);
}
