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

    public Member updateMember(Long memberId, MemberRequestDTO.MemberUpdateRequestDTO memberUpdateRequestDTO);

    public String joinSocialMember(String email, String id);

    void createFavoriteCoach(Long userId, Long coachId);


    public String classifyUsers(String Email, Role role);

    public Optional<Member> insertInfo(Long memberId, MemberRequestDTO.MemberProfileRegisterDTO request);

}
