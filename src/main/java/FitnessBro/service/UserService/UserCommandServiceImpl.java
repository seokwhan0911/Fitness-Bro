package FitnessBro.service.UserService;

import FitnessBro.converter.UserConverter;
import FitnessBro.domain.user.Entity.Users;
import FitnessBro.respository.UserRespository;
import FitnessBro.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRespository userRespository;

    @Override
    @Transactional
    public Users joinUser(UserRequestDTO.JoinDTO request) {

        Users newUser = UserConverter.toUser(request);

        return userRespository.save(newUser);
    }
}
