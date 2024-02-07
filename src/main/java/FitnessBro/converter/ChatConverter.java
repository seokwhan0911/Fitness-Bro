package FitnessBro.converter;

import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.service.ChatService.ChatRoomService;
import FitnessBro.web.dto.Chat.ChatMessageDTO;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import lombok.RequiredArgsConstructor;

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
                .map(chatMessage -> toChatMessageDTO(chatMessage))
                .collect(Collectors.toList());
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

    public static ChatRoomResponseDTO.ChatRoomSimpleDTO toMemberChatRoomSimpleDTO(ChatRoom chatRoom){
        return ChatRoomResponseDTO.ChatRoomSimpleDTO.builder()
                .chatRoomId(chatRoom.getId())
                .lastChatMessage(chatRoom.getLastChatMessage())
                .partnerName(chatRoom.getMember().getNickname())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<ChatRoomResponseDTO.ChatRoomSimpleDTO> toMemberChatRoomSimpleListDTO(List<ChatRoom> chatRoomList){
        return chatRoomList.stream()
                .map(chatRoom -> toMemberChatRoomSimpleDTO(chatRoom))
                .collect(Collectors.toList());
    }

    public static ChatRoomResponseDTO.ChatRoomSimpleDTO toCoachChatRoomSimpleDTO(ChatRoom chatRoom){
        return ChatRoomResponseDTO.ChatRoomSimpleDTO.builder()
                .chatRoomId(chatRoom.getId())
                .lastChatMessage(chatRoom.getLastChatMessage())
                .partnerName(chatRoom.getCoach().getNickname())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<ChatRoomResponseDTO.ChatRoomSimpleDTO> toCoachChatRoomSimpleListDTO(List<ChatRoom> chatRoomList){
        return chatRoomList.stream()
                .map(chatRoom -> toCoachChatRoomSimpleDTO(chatRoom))
                .collect(Collectors.toList());
    }
}
