package FitnessBro.respository;

import FitnessBro.domain.coach.Entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long>{
    List<Coach> findAll();

    Coach findByNickname(String nickname);
}
