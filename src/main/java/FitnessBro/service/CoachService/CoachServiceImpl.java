package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.gym.Entity.Gym;
import FitnessBro.domain.user.Entity.Member;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.GymRepository;
import FitnessBro.web.dto.CoachResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static FitnessBro.converter.CoachConverter.toCoachDTO;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final GymRepository gymRepository;
    public Coach getCoachById(Long coachId){
        return coachRepository.getById(coachId);
    }



    @Override
    @Transactional
    public List<Coach> getCoachList(){

        List<Coach> coaches = coachRepository.findAll();
        return coaches;
    }


}
