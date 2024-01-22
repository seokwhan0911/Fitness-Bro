package FitnessBro.service.CoachService;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.gym.Entity.Gym;
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

   // public List<CoachResponseDTO.CoachDTO> getCoachList(Long gymId){

       // List<Coach> coaches = coachRepository.findAllByGym(gymId);

        //return coaches.stream()
          //      .map(coach -> toCoachDTO(coach)) // toCoachDTO 메서드를 사용하여 Coach를 CoachDTO로 변환
            //    .collect(Collectors.toList()); // collect를 사용하여 리스트로 반환.
    //}

    @Override
    @Transactional
    public List<Coach> getCoachList(){

        List<Coach> coaches = coachRepository.findAll();
        return coaches;
    }

//    public Optional<Gym> findGym(Long gymId){
//        return gymRepository.findById(gymId);
//    }

//    public Page<Coach> getCoachList(Long gymId, Integer page){
//        Gym gym = gymRepository.findById(gymId).get();

        //Page<Coach> coachPage = coachRepository.findAllByGym(gym, PageRequest.of(page, 10));
        //return coachPage;
    //}




}
