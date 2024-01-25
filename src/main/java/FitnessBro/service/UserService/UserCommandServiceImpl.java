package FitnessBro.service.UserService;

import FitnessBro.converter.UserConverter;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.repository.UserRepository;
import FitnessBro.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Member joinUser(UserRequestDTO.JoinDTO request) {

        Member newUser = UserConverter.toUser(request);

        return userRepository.save(newUser);
    }
}
