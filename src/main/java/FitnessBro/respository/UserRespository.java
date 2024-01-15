package FitnessBro.respository;

import FitnessBro.domain.user.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<Users, Long> {
}
