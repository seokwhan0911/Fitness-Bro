package FitnessBro.respository;


import FitnessBro.domain.Member;

import io.jsonwebtoken.Claims;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Member findByEmail(Claims email);

    boolean existsByEmail(String email);

    Optional<Member> findById(Long memberId);

    String deleteMemberByEmail(String email);


}
