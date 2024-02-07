package FitnessBro.respository;

import FitnessBro.domain.Chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findById(Long roomId);
    List<ChatRoom> findAllByMemberId(Long memberId);
    List<ChatRoom> findAllByCoachId(Long coachId);

}
