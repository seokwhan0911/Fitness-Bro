package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;

    public Coach getCoachById(Long coachId){
        return coachRepository.getById(coachId);
    }



}
