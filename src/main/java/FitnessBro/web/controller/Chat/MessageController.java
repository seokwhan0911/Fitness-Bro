package FitnessBro.web.controller.Chat;

import FitnessBro.converter.ChatConverter;
import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.service.ChatService.ChatMessageService;
import FitnessBro.service.ChatService.ChatRoomService;
import FitnessBro.web.dto.Chat.ChatMessageRequestDTO;
import FitnessBro.web.dto.Chat.ChatMessageResponseDTO;
import FitnessBro.web.dto.Chat.ChatRoomRequestDTO;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    // 채팅방 생성 : memberId와 coachId로 채팅방 생성 후 채팅방 id, 최근15개 메세지 리턴
    // /pub/connect 엔드포인트로 채팅하기 누를시.
    @MessageMapping("/connect")
    @SendTo("/queue/{memberId}/{coachId}") // 여기를 구독하고 있어야 함
    public ChatRoomResponseDTO.ChatRoomInfoDTO createRoom(@RequestBody @Valid ChatRoomRequestDTO request) {

        ChatRoom newChatRoom = new ChatRoom();
        ChatRoom chatRoom = chatRoomService.findChatRoomByMemberIdAndCoachId(request.getMemberId(),request.getCoachId());

        if(chatRoom == null){
            chatRoom = chatRoomService.createRoom(newChatRoom.getId(), request.getMemberId(), request.getCoachId());
        }

        List<ChatMessage> latestChatMessages = chatMessageService.findChatMessagesWithPaging(request.getPageNum(), chatRoom.getId());

        List<ChatMessageRequestDTO> chatMessageRequestDTOList = ChatConverter.toChatMessageListDTO(latestChatMessages);

        ChatRoomResponseDTO.ChatRoomInfoDTO chatRoomInfoDto = ChatConverter.toChatRoomInfoDTO(chatRoom, chatMessageRequestDTOList);

        return chatRoomInfoDto;
    }

    @MessageMapping("/send") //전체경로는 "/sub/queue/chat/{roomId}이다.
    public void message(@RequestBody ChatMessageRequestDTO request) {

        ChatRoom chatRoom = chatRoomService.findById(request.getRoomId());
        chatRoom.setUpdatedAt(LocalDateTime.now());

        ChatMessage chatMessage = ChatConverter.toChatMessage(request, chatRoom);

        chatMessageService.ChatMessageSave(chatMessage);

        ChatMessageResponseDTO chatMessageResponseDTO = ChatConverter.toChatMessageResponseDTO(chatMessage);

        simpMessageSendingOperations.convertAndSend("sub/queue/chat" + request.getRoomId(),chatMessageResponseDTO); //전체경로는 "/sub/queue/chat/{roomId}이다.

    }


//    @MessageMapping("/chat/message")
//    public void enter(ChatMessage message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
//            message.setMessage(message.getSender()+"님이 입장하였습니다.");
//        }
//
//        messageSendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
//
//    }
}
