package FitnessBro.repository;

import FitnessBro.domain.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long> {
}
