package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;

import java.util.List;


public interface CoachService {


    public Coach getCoachById(Long coachId);



    //public List<CoachResponseDTO.CoachDTO> getCoachList(Long gymId);

    public List<Coach> getCoachList();



}
