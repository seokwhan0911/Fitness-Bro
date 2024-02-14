package FitnessBro.service.GymService;

import FitnessBro.domain.Gym;
import FitnessBro.respository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService{

    private final GymRepository gymRepository;

    public List<Gym> getGymListByKeyWord(String keyword){
        List<Gym> gyms = gymRepository.findGymByNameContaining(keyword);

        return gyms;

    }
    @Override
    @Transactional
    public Gym getGymByAddress(String address){
        Gym gym = gymRepository.findGymByAddress(address);

        return gym;
    }



}
