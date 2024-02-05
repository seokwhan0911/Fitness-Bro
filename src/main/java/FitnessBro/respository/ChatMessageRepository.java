package FitnessBro.respository;


import FitnessBro.domain.Chat.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllByRoomId(Long roomId);

    Page<ChatMessage> findListsByRoomId(Long roomId, PageRequest pageRequest);

    ChatMessage findByChatRoomId(Long roomId);

}
