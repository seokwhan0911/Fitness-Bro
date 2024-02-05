package FitnessBro.converter;

import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.web.dto.Chat.ChatMessageDTO;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;

public class ChatConverter {

    public static ChatRoomResponseDTO.ChatRoomCreateResponseDTO toChatRoomCreateResponseDTO(ChatRoom chatRoom){
        return ChatRoomResponseDTO.ChatRoomCreateResponseDTO.builder()
                .roomId(chatRoom.getId())
                .message(chatRoom.getId() + "번 방 생성이 완료 되었습니다.")
                .build();
    }
}
