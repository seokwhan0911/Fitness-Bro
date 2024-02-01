package FitnessBro.respository;

import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.review.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register,Long> {
    Long countByCoachId(Long coachId);

}


