package FitnessBro.web.dto;

import FitnessBro.web.dto.Login.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDTO {
    String userToken;
    Long userId;
    Role role;
}
