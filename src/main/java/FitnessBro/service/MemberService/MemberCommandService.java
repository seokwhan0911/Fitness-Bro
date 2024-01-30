package FitnessBro.service.MemberService;

import FitnessBro.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    String joinMember(MemberRequestDTO.JoinDTO request);

    String login(String email, String password);
}
