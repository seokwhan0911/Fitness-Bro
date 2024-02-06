package FitnessBro.service.CoachService;


import FitnessBro.domain.Coach;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.domain.Coach;

import java.util.List;


public interface CoachService {

    public Coach getCoachById(Long coachId);

    public List<Coach> getCoachList();

    public Coach updateCoach(Long coachId, CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO);
}
