package FitnessBro.service.CoachService;


import FitnessBro.domain.Coach;

import java.util.List;


public interface CoachService {

    public Coach getCoachById(Long coachId);

    public List<Coach> getCoachList();

}
