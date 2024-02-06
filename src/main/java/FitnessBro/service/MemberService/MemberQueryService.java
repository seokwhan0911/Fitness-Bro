package FitnessBro.service.MemberService;

import FitnessBro.domain.Coach;

import java.util.List;

public interface MemberQueryService {
    List<Coach> getFavoriteCoachList(Long memberId);
}
