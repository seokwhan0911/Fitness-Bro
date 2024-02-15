package FitnessBro.service.ChatService;

import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;

import java.util.List;

public interface ChatMessageService {

    List<ChatMessage> findAllByChatRoomId(Long roomId);

    List<ChatMessage> findChatMessagesWithPaging(int page, Long roomId);
    ChatMessage findMessageByRoomId(Long roomId);

    void ChatMessageSave(ChatMessage chatMessage);

    List<ChatRoomResponseDTO.ChatRoomSimpleDTO> sortChatMessageDTO(List<ChatRoomResponseDTO.ChatRoomSimpleDTO> chatRoomSimpleDTOList);
}
