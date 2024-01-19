package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewRepository A;

    public Coach getCoachById(Long coachId){
        return coachRepository.getById(coachId);
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
//awefwaef
        coachRepository.save(coach);
    }

}
