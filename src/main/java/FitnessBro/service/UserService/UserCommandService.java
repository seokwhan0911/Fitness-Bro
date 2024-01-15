package FitnessBro.service.UserService;

import FitnessBro.domain.user.Entity.Users;
import FitnessBro.web.dto.UserRequestDTO;

public interface UserCommandService {

    Users joinUser(UserRequestDTO.JoinDTO request);
}
