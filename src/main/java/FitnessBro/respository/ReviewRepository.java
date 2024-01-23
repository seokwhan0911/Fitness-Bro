package FitnessBro.respository;

import FitnessBro.domain.review.Entity.Review;
import FitnessBro.domain.user.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByCoachId(Long coachId);
}
