package FitnessBro.respository;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    Register findByMemberAndCoach(Member member,Coach coach);
    List<Register> findAllByCoach(Coach coach);
    List<Register> findAllByMember(Member member);
    Register deleteRegisterById(Long registerId);
    Long countByCoachId(Long coachId);
    Long countByMemberId(Long memberId);
}



