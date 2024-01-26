package FitnessBro.service.RegisterService;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.user.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface RegisterService {


    Register getRegisterByMemberCoach(Member member, Coach coach);

    List<Register> getRegisterListByCoach(Coach coach);

    List<Register> getRegisterListByMember(Member member);

    List<Register> successRegisterList(List<Register> registerList);

    Register registerSetting(Member member, Coach coach);

    Register registerCoachSetting(Member member, Coach coach);
}
