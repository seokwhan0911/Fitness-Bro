package FitnessBro.service.RegisterService;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Register;
import FitnessBro.domain.Member;

import java.util.List;

public interface RegisterService {


    Register getRegisterByMemberCoach(Member member, Coach coach);

    List<Register> getRegisterListByCoach(Coach coach);

    List<Register> getRegisterListByMember(Member member);

    List<Register> successRegisterList(List<Register> registerList);

    Register registerSetting(Member member, Coach coach);

    Register registerApproveSetting(Member member, Coach coach);

    public Long getMatchNumCoach(Long coachId);
    public Long getMatchNumMember(Long memberId);

    List<Register> getRequestRegisterList(List<Register> registerList);

    Register registerRejectSetting(Member member, Coach coach);

}
