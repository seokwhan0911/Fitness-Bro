package FitnessBro.service.ChatService;

import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;

import java.util.List;

public interface ChatMessageService {

    List<ChatMessage> findAllByChatRoomId(Long roomId);

    List<ChatMessage> findChatMessagesWithPaging(int page, Long roomId);
    ChatMessage findMessageByRoomId(Long roomId);

    void ChatMessageSave(ChatMessage chatMessage);


}
