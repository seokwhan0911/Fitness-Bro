package FitnessBro.respository;

import FitnessBro.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findById(Long gymId);

    public Gym findGymByAddress(String address);

    List<Gym> findGymByNameContaining(String keyword);

    @Query("SELECT g FROM Gym g WHERE g.region = :region AND g.subAddress = :subAddress AND g.detailAddress = :detailAddress")
    Gym findGymByRegionSubAddressDetailAddress(
            @Param("region") String region,
            @Param("subAddress") String subAddress,
            @Param("detailAddress") String detailAddress
    );
}
