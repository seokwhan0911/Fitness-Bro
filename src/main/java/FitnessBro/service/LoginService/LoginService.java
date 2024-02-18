package FitnessBro.service.LoginService;

import FitnessBro.web.dto.Login.Role;

public interface LoginService {

    public String decodeJwt(String token);

    public Long getIdByEmail(String email);

    Role getRoleByEmail(String email);
}
