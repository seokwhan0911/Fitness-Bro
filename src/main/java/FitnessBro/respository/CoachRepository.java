package FitnessBro.respository;

import FitnessBro.domain.coach.Entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
