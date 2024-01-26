package FitnessBro.service.UserService;

import FitnessBro.domain.user.Entity.Member;

import java.util.Optional;

public interface MemberCommandService {
     Member getMemberById(Long memberId);

}
