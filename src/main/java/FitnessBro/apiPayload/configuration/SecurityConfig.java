package FitnessBro.apiPayload.configuration;

import FitnessBro.apiPayload.Utill.JwtTokenUtil;
import FitnessBro.service.UserService.MemberCommandService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberCommandService memberCommandService;

    @Value("${jwt.token.secret}")
    private String seceretKey;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/members/sign-up").permitAll()
                                .requestMatchers("/members/login").permitAll()
                                .requestMatchers("/coaches/sign-up").permitAll()
                                .requestMatchers("/coaches/login").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(memberCommandService, seceretKey), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
