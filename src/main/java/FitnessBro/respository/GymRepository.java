package FitnessBro.respository;

import FitnessBro.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findById(Long gymId);

    public Gym findGymByAddress(String address);

    List<Gym> findGymByNameContaining(String keyword);
}
