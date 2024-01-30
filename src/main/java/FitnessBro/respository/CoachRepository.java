package FitnessBro.respository;

import FitnessBro.domain.coach.Entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long>{

    List<Coach> findAll();

}
