package FitnessBro.service.LoginService;

import io.jsonwebtoken.Claims;

public interface LoginService {

    public String decodeJwt(String token);

    public Long getIdByEmail(String email);
}
