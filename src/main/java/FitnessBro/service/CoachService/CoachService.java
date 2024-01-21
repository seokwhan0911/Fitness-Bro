package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.respository.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CoachService {


    public Coach getCoachById(Long coachId);

    public void addCoach();



}
