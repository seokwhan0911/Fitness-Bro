package FitnessBro.apiPayload.configuration;

import FitnessBro.apiPayload.Utill.JwtTokenUtil;
import FitnessBro.service.UserService.MemberCommandService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final MemberCommandService memberCommandService;
    private final String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request 헤더에서 토큰 꺼내기
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        // 토큰 안보내면 block
        if(authorization == null || !authorization.startsWith("Bearer ")){
            log.error("authorization 을 잘못 보냈습니다.");
            filterChain.doFilter(request,response);
            return;
        }

        // 토큰 꺼내기
        String token = authorization.split(" ")[1];

        // 토큰이 Expired 되었는지 여부
        if(JwtTokenUtil.isExpired(token, secretKey)){
            log.error("Token이 만료 되었습니다.");
            filterChain.doFilter(request,response);
            return;
        }
        // 토큰에서 memberId 꺼내기
        String memberId = JwtTokenUtil.getMemberId(token, secretKey);
        log.info("memberId:{}", memberId);
        //권한부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberId, null, List.of(new SimpleGrantedAuthority("MEMBER")));

        //Detail을 넣어준다
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
