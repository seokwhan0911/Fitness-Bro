package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;

import java.util.List;


public interface CoachService {


    public Coach getCoachById(Long coachId);





    public List<Coach> getCoachList();



}
