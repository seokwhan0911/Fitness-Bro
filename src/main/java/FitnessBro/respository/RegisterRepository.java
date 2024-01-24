package FitnessBro.respository;

import FitnessBro.converter.CoachConverter;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.user.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Member> findAllByCoach(Coach coach);

}
