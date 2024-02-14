package FitnessBro.service.GymService;

import FitnessBro.domain.Gym;

import java.util.List;
import java.util.Optional;

public interface GymService {

    public List<Gym> getGymListByKeyWord(String keyword);

    Gym getGymByAddress(String address);

}
