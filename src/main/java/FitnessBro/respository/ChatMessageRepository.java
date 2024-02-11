package FitnessBro.respository;


import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllByChatRoomId(Long roomId);

    Page<ChatMessage> findListsByChatRoomId(Long roomId, PageRequest pageRequest);

    ChatMessage findByChatRoomId(Long roomId);

    List<ChatMessage> findAllTopByChatRoomOrderByCreatedAtDesc(ChatRoom chatRoom);

    ChatMessage findTopByChatRoomOrderByCreatedAtDesc(ChatRoom chatRoom);

}
