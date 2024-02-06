package FitnessBro.respository;

import FitnessBro.domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorites, Long> {

    List<Favorites> findAllByMemberId(Long memberId);
}
