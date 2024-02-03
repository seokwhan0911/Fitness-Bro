package FitnessBro.respository;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.register.Entity.Register;
import FitnessBro.domain.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    Register findByMemberAndCoach(Member member,Coach coach);
    List<Register> findAllByCoach(Coach coach);
    List<Register> findAllByMember(Member member);
}
