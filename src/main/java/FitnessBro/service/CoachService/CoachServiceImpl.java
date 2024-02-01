package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.gym.Entity.Gym;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.review.Entity.Review;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.GymRepository;
import FitnessBro.respository.RegisterRepository;
import FitnessBro.respository.ReviewRepository;
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
import static FitnessBro.converter.CoachConverter.tocoachMyPageDTO;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final GymRepository gymRepository;
    private final ReviewRepository reviewRepository;
    private final RegisterRepository registerRepository;

    public Coach getCoachById(Long coachId){
        return coachRepository.getById(coachId);
    }

//    public List<CoachResponseDTO.CoachDTO> getCoachList(Long gymId){
//
//        List<Coach> coaches = coachRepository.findAllByGym(gymId);
//
//        return coaches.stream()
//                .map(coach -> toCoachDTO(coach)) // toCoachDTO 메서드를 사용하여 Coach를 CoachDTO로 변환
//                .collect(Collectors.toList()); // collect를 사용하여 리스트로 반환.
//    }

    @Override
    @Transactional
    public List<Coach> getCoachList(){

        List<Coach> coaches = coachRepository.findAll();
        return coaches;
    }

    public Long getMatchNum(Long coachId){
        return registerRepository.countByCoachId(coachId);
    }

    public Long getReviewNum(Long coachId){
        return reviewRepository.countByCoachId(coachId);
    }

    public CoachResponseDTO.CoachMyPageDTO getCoachMyPage(Long coachId) {
        //coachId로 coach entity 가져옴
        Coach coach = coachRepository.getById(coachId);

        return tocoachMyPageDTO(coach, getMatchNum(coachId), getReviewNum(coachId));
    }
}
