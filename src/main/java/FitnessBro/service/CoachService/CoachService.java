package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.gym.Entity.Gym;
import FitnessBro.respository.CoachRepository;
import FitnessBro.web.dto.CoachResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CoachService {


    public Coach getCoachById(Long coachId);



    //public List<CoachResponseDTO.CoachDTO> getCoachList(Long gymId);

    public List<Coach> getCoachList();

    //Optional<Gym> findGym(Long gymId);

    //Page<Coach> getCoachList(Long gymId, Integer page);

}
