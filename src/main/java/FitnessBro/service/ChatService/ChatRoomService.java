package FitnessBro.service.ChatService;

import FitnessBro.domain.Chat.ChatRoom;

import java.util.List;

public interface ChatRoomService {

   // List<ChatRoom> findAllRoom();
    ChatRoom findById(Long roomId);
    ChatRoom createRoom(Long roomId, Long memberId, Long coachId);
}
