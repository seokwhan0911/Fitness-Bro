package FitnessBro.service.LoginService;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.MemberRepository;
import FitnessBro.web.dto.Login.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;
    private final CoachRepository coachRepository;

    @Value("${jwt.secret}")
    private String key;
    @Override
    public String decodeJwt(String token){
        Claims email = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        String stringEmail = email.get("email", String.class);

        return stringEmail;
    }
    @Override
    public Long getIdByEmail(String email){
        Long id = null;

        if(memberRepository.findMemberByEmail(email) == null) {
            Coach coach = coachRepository.findCoachByEmail(email);
            id = coach.getId();
        }
        else{
            Member member = memberRepository.findMemberByEmail(email);
            id = member.getId();
        }
        return id;
    }

    @Override
    public Role getRoleByEmail(String email){
        Role role = null;
        if(memberRepository.findMemberByEmail(email) == null) {
            Coach coach = coachRepository.findCoachByEmail(email);
            role = Role.COACH;
        }
        else{
            Member member = memberRepository.findMemberByEmail(email);
            role = Role.MEMBER;
        }

        return role;
    }


}
