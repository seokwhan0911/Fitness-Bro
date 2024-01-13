package FitnessBro.service.UserService;

import FitnessBro.domain.user.Entity.User;
import FitnessBro.web.dto.UserRequestDTO;

public interface UserCommandService {

    User joinUser(UserRequestDTO.JoinDTO request);
}
