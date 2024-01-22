package FitnessBro.respository;


import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.gym.Entity.Gym;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long>{
    //List<Coach> findAllByGym(Long gymId);
    List<Coach> findAll();
    //Page<Coach> findAllByGym(Gym gym, PageRequest pageRequest);
}
