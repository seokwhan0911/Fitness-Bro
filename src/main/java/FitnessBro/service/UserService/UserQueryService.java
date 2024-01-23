package FitnessBro.service.UserService;

import FitnessBro.domain.coach.Entity.Coach;

import java.util.List;

public interface UserQueryService {
    List<Coach> getFavoriteCoachList(Long memberId);
}
