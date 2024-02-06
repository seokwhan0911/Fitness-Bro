package FitnessBro.service.CoachService;


import FitnessBro.aws.s3.AmazonS3Manager;
import FitnessBro.domain.Coach;
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
    private final GymRepository gymRepository;
    private final ReviewRepository reviewRepository;
    private final RegisterRepository registerRepository;
    private final AmazonS3Manager s3Manager;

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
    public Coach updateCoach(Long coachId, CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO){
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        coach.update(coachUpdateRequestDTO);
        coachRepository.save(coach);
        return coach;
    }

}
