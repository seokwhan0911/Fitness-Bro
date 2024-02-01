package FitnessBro.service.MemberService;

import FitnessBro.domain.coach.Entity.Coach;

import java.util.List;

public interface MemberQueryService {
    List<Coach> getFavoriteCoachList(Long memberId);
}
