package FitnessBro.service.ChatService;

import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;

import java.util.List;

public interface ChatService {

    List<ChatRoom> findAllRoom();
    ChatRoom findById(String roomId);
    ChatRoom createRoom(String name);
}
