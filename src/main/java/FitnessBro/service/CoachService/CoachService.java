package FitnessBro.service.CoachService;


import FitnessBro.domain.Coach;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import FitnessBro.domain.Coach;
import FitnessBro.web.dto.Coach.CoachRequestDTO;

import java.util.List;
import java.util.Optional;


public interface CoachService {

    public Coach getCoachById(Long coachId);

    public List<Coach> getCoachList();

    public Optional<Coach> insertInfo(Long coachId, CoachRequestDTO.CoachProfileRegisterDTO request);

    public Coach updateCoach(Long coachId, CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO);
}
