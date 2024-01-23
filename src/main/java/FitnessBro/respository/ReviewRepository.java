package FitnessBro.respository;

import FitnessBro.domain.review.Entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select rv from Review rv inner join rv.coach c where c.id = :coachId" )
    List<Review> findByCoachId(@Param("coachId") Long coachId);




}