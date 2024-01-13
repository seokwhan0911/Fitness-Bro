package FitnessBro.service.UserService;

import FitnessBro.converter.UserConverter;
import FitnessBro.domain.user.Entity.User;
import FitnessBro.repository.UserRepository;
import FitnessBro.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDTO request) {

        User newUser = UserConverter.toUser(request);

        return userRepository.save(newUser);
    }
}
