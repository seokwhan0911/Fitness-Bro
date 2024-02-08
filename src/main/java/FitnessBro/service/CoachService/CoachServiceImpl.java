package FitnessBro.service.CoachService;


import FitnessBro.aws.s3.AmazonS3Manager;
import FitnessBro.domain.Coach;
import FitnessBro.respository.CoachRepository;
import FitnessBro.respository.GymRepository;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final GymRepository gymRepository;
    private final AmazonS3Manager s3Manager;

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
    public Optional<Coach> insertInfo(Long coachId, CoachRequestDTO.CoachProfileRegisterDTO request){
        Optional<Coach> coach = coachRepository.findById(coachId);

        coach.ifPresent(t -> {
            t.setNickname(request.getNickname());
            t.setSchedule(request.getSchedule());
            t.setComment(request.getComment());
            t.setPrice(request.getPrice());

            coachRepository.save(t);
        });

        return coach;
    }

}
