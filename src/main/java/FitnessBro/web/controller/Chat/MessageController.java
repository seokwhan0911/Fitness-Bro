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
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
   // private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final SimpMessagingTemplate simpMessagingTemplate;

    // 채팅방 생성 : memberId와 coachId로 채팅방 생성 후 채팅방 id
    // /pub/connect 엔드포인트로 채팅하기 누를시.
    @MessageMapping("/connect")
    public void createRoom(@RequestBody @Valid ChatRoomRequestDTO request) {

        ChatRoom newChatRoom = new ChatRoom();
        ChatRoom chatRoom = chatRoomService.findChatRoomByMemberIdAndCoachId(request.getMemberId(),request.getCoachId());

        if(chatRoom == null){
            chatRoom = chatRoomService.createRoom(newChatRoom.getId(), request.getMemberId(), request.getCoachId());
        }

        ChatRoomResponseDTO.ChatRoomInfoDTO chatRoomInfoDto = ChatConverter.toChatRoomInfoDTO(chatRoom);

       // simpMessageSendingOperations.convertAndSend("/sub/queue/" + request.getMemberId() + "/" + request.getCoachId(),chatRoomInfoDto);

    }

    @MessageMapping("/send")
    public ChatMessageRequestDTO message(@RequestBody ChatMessageRequestDTO request) {

        ChatRoom chatRoom = chatRoomService.findById(request.getChatRoomId());
        chatRoom.setUpdatedAt(LocalDateTime.now());

        ChatMessage chatMessage = ChatConverter.toChatMessage(request, chatRoom);

        chatMessageService.ChatMessageSave(chatMessage);

        ChatMessageResponseDTO chatMessageResponseDTO = ChatConverter.toChatMessageResponseDTO(chatMessage);

        //simpMessageSendingOperations.convertAndSend("/sub/queue/chat/" + request.getRoomId(),chatMessageResponseDTO); //전체경로는 "/sub/queue/chat/{roomId}이다.
        simpMessagingTemplate.convertAndSendToUser(request.getChatRoomId().toString(),"/private",request); // "/user/7/private" 7은 String임
        log.info("메시지를 전송했습니다: {}", chatMessageResponseDTO);

        return request;
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
