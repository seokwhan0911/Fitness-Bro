package FitnessBro.service.RegisterService;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.user.Entity.Member;

import java.util.List;

public interface RegisterService {
    List<Member> getMemberRegisterList(Coach coach);
}
