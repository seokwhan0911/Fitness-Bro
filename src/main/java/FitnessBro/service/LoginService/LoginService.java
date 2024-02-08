package FitnessBro.service.LoginService;

import io.jsonwebtoken.Claims;

public interface LoginService {

    public Claims decodeJwt(String token);

    public Long getIdByEmail(Claims email);
}
