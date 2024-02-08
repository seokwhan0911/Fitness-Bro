package FitnessBro.service.LoginService;

import FitnessBro.domain.Member;
import FitnessBro.respository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Value("${jwt.secret}")
    private String key;
    @Override
    public Claims decodeJwt(String token){
        Claims email = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        return email;
    }
    @Override
    public Long getIdByEmail(Claims email){
        Member member = memberRepository.findByEmail(email);

        Long id = member.getId();
        return id;
    }

}
