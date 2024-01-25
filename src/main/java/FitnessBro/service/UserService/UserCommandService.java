package FitnessBro.service.UserService;

import FitnessBro.domain.member.Entity.Member;
import FitnessBro.web.dto.UserRequestDTO;

public interface UserCommandService {

    Member joinUser(UserRequestDTO.JoinDTO request);
}
