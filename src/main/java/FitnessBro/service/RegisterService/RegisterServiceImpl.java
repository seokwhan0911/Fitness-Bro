package FitnessBro.service.RegisterService;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.MemberRepository;
import FitnessBro.respository.RegisterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    public final RegisterRepository registerRepository;
    @Override
    @Transactional
    public List<Member> getMemberRegisterList(Coach coach){

        List<Member> memberRegisterList = registerRepository.findAllByCoach(coach);

        return memberRegisterList;
    }
}
