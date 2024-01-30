package FitnessBro.respository;

import FitnessBro.domain.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String userId);

    boolean existsByEmail(String email);


}
