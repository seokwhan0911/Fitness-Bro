package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.respository.CoachRepository;
import FitnessBro.web.dto.CoachResponseDTO;
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

    public List<CoachResponseDTO.CoachDTO> getCoachList(Coach coach){
        List<Coach> coaches = coachRepository.findAllByAddress(coach.getAddress());
    }



    public void addCoach() {
        Coach coach = new Coach();
        coach.setId(1L);
        coach.setAge(3);
        coach.setEmail("awef");
        coach.setAddress("awef");
        coach.setName("awef");
        coach.setNickname("awef");
        coach.setRating(4L);
        coach.setIntroduction("awef");
        coach.setSchedule("awef");
        coach.setPassword("awef");
        coach.setComment("awef");
        coach.setPrice(10000);

        coachRepository.save(coach);
    }

}
