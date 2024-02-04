package FitnessBro.service.CoachService;


import FitnessBro.apiPayload.Utill.StringUtils;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.gym.Entity.Gym;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.GymRepository;
import FitnessBro.respository.RegisterRepository;
import FitnessBro.respository.ReviewRepository;

import FitnessBro.service.RegisterService.RegisterService;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static FitnessBro.converter.CoachConverter.toCoachDTO;
import static FitnessBro.converter.CoachConverter.toCoachMyPageDTO;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final ReviewRepository reviewRepository;
    private final RegisterRepository registerRepository;
    private final CoachService coachService;
    private final RegisterService registerService;

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

    @Override
    @Transactional
    public Long getMatchNum(Long coachId){
        Coach coach = coachService.getCoachById(coachId);
        List<Register> registerList = registerService.getRegisterListByCoach(coach);
        List<Register> trueRegisterList = registerService.successRegisterList(registerList);

        return (long)trueRegisterList.size();
    }

    @Override
    @Transactional
    public Long getReviewNum(Long coachId){
        return reviewRepository.countByCoachId(coachId);
    }

    @Override
    @Transactional
    public Coach updateCoach(Long coachId, CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO){
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        coach.update(coachUpdateRequestDTO);
        return coachRepository.save(coach);
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
