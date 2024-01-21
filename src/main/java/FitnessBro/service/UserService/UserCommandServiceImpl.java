package FitnessBro.service.UserService;

import FitnessBro.converter.UserConverter;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final MemberRepository userRespository;

    @Override
    @Transactional
    public Member joinUser(UserRequestDTO.JoinDTO request) {

        Member newUser = UserConverter.toUser(request);

        return userRespository.save(newUser);
    }
}
