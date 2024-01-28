package FitnessBro.apiPayload.configuration;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/members/sign-up").permitAll()
                                .requestMatchers("/members/login").permitAll()
                                .requestMatchers("/coaches/sign-up").permitAll()
                                .requestMatchers("/coaches/login").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}
