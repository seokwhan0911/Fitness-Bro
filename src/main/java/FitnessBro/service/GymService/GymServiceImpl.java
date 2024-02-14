package FitnessBro.service.GymService;

import FitnessBro.domain.Gym;
import FitnessBro.respository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService{

    private final GymRepository gymRepository;

    public List<Gym> getGymListByKeyWord(String keyword){

        return null;

    }


}
