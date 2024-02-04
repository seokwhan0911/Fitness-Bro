package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.gym.Entity.Gym;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.GymRepository;
import FitnessBro.respository.RegisterRepository;
import FitnessBro.respository.ReviewRepository;

import FitnessBro.service.RegisterService.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static FitnessBro.converter.CoachConverter.toCoachDTO;
import static FitnessBro.converter.CoachConverter.tocoachMyPageDTO;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final GymRepository gymRepository;
    private final ReviewRepository reviewRepository;
    private final RegisterRepository registerRepository;

    @Override
    @Transactional
    public Coach getCoachById(Long coachId){
        return coachRepository.getById(coachId);
    }



    @Override
    @Transactional
    public List<Coach> getCoachList(){

        List<Coach> coaches = coachRepository.findAll();
        return coaches;
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
