package FitnessBro.respository;

import FitnessBro.domain.gym.Entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findById(Long gymId);
}
