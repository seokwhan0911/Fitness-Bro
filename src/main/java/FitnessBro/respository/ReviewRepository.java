package FitnessBro.respository;

import FitnessBro.domain.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select rv from Review rv inner join rv.coach c where c.id = :coachId" )
    List<Review> findByCoachId(@Param("coachId") Long coachId);

    Long countByCoachId(Long coachId);//Coach가 받은 리뷰 개수 세는 메서드

    Long countByMemberId(Long memberId);//Member가 작성한 리뷰 개수 세는 메서드
    List<Review> findAllByMemberId(Long memberId);
}