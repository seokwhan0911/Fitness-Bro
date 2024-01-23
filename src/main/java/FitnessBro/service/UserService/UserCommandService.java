package FitnessBro.service.UserService;

import FitnessBro.domain.user.Entity.Member;
import FitnessBro.web.dto.User.UserRequestDTO;

public interface UserCommandService {

    Member joinUser(UserRequestDTO.JoinDTO request);
}
