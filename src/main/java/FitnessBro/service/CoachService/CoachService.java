package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import org.springframework.stereotype.Service;


public interface CoachService {

    public Coach getCoachById(Long coachId);
}
