package FitnessBro.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDTO {
    String userToken;
    Long userId;
}
