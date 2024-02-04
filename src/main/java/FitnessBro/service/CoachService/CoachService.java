package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;

import java.util.List;


public interface CoachService {

    public Coach getCoachById(Long coachId);

    public List<Coach> getCoachList();

    public Coach updateCoach(Long coachId, CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO);
}
