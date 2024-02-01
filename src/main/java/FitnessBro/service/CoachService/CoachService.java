package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.web.dto.Coach.CoachResponseDTO;

import java.util.List;


public interface CoachService {


    public Coach getCoachById(Long coachId);



    //public List<CoachResponseDTO.CoachDTO> getCoachList(Long gymId);

    public List<Coach> getCoachList();


    public Long getMatchNum(Long coachId);
    public Long getReviewNum(Long coachId);


}
