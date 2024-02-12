package FitnessBro.respository;

import FitnessBro.domain.Coach;
import FitnessBro.domain.CoachImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachImageRepository extends JpaRepository<CoachImage, Long> {
    Boolean existsByCoach(Coach coach);

}
