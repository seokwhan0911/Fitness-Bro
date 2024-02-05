package FitnessBro.converter;

import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.web.dto.Chat.ChatMessageDTO;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ChatConverter {

    public static ChatRoomResponseDTO.ChatRoomCreateResponseDTO toChatRoomCreateResponseDTO(ChatRoom chatRoom){
        return ChatRoomResponseDTO.ChatRoomCreateResponseDTO.builder()
                .roomId(chatRoom.getId())
                .message(chatRoom.getId() + "번 방 생성이 완료 되었습니다.")
                .build();
    }

    public static ChatMessageDTO toChatMessageDTO(ChatMessage chatMessage){

        return ChatMessageDTO.builder()
                .message(chatMessage.getMessage())
                .sender(chatMessage.getSender())
                .build();
    }

    public static List<ChatMessageDTO> toChatMessageListDTO(List<ChatMessage> chatMessageList){
        return chatMessageList.stream()
                .map(chatMessage -> toChatMessageDTO(chatMessage)) // toCoachDTO 메서드를 사용하여 Coach를 CoachDTO로 변환
                .collect(Collectors.toList()); // collect를 사용하여 리스트로 반환.
    }

    public static ChatRoomResponseDTO.ChatRoomInfoDTO toChatRoomInfoDTO(ChatRoom chatRoom, List<ChatMessageDTO> chatMessageDTOList){
        return ChatRoomResponseDTO.ChatRoomInfoDTO.builder()
                .chatRoomId(chatRoom.getId())
                .latestChatMessages(chatMessageDTOList)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ChatMessage toChatMessage(ChatMessageDTO message, ChatRoom chatRoom){

        return ChatMessage.builder()
                .chatRoom(chatRoom)
                .message(message.getMessage())
                .createdAt(LocalDateTime.now())
                .sender(message.getSender())
                .build();
    }
}
