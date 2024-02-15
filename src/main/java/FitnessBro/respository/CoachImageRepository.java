package FitnessBro.respository;

import FitnessBro.domain.Coach;
import FitnessBro.domain.CoachImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachImageRepository extends JpaRepository<CoachImage, Long> {
    List<CoachImage> findByCoachId(Long coachId);
    Boolean existsByCoachId(Long coachId);
}
