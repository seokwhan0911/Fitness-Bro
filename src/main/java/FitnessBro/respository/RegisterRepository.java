package FitnessBro.respository;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Register;
import FitnessBro.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import FitnessBro.domain.Register;
import FitnessBro.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    Register findByMemberAndCoach(Member member,Coach coach);
    List<Register> findAllByCoach(Coach coach);
    List<Register> findAllByMember(Member member);
    Long countByCoachId(Long coachId);
    Long countByMemberId(Long memberId);
}



