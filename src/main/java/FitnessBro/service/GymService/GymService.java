package FitnessBro.service.GymService;

import FitnessBro.domain.Gym;

import java.util.List;

public interface GymService {

    public List<Gym> getGymListByKeyWord(String keyword);

    Gym getGymByAddress(String region, String subAddress, String detailAddress);

}
