package FitnessBro.service.MemberService;


import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Login.Role;
import FitnessBro.web.dto.Member.MemberRequestDTO;
import io.jsonwebtoken.Claims;

import java.util.Optional;

public interface MemberCommandService {
     Member getMemberById(Long memberId);

    String joinMember(MemberRequestDTO.JoinDTO request);

    String login(String email, String password);

    public String joinSocialMember(String email, String id);

    public Claims decodeJwt(String token);

    public String classifyUsers(Claims userInfo, Role role);

    public Optional<Member> insertInfo(Long memberId, MemberRequestDTO.MemberProfileRegisterDTO request);

}
