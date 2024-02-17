package FitnessBro.web.controller.Chat;

import FitnessBro.converter.ChatConverter;
import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.service.ChatService.ChatMessageService;
import FitnessBro.service.ChatService.ChatRoomService;
import FitnessBro.web.dto.Chat.ChatMessageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate simpMessagingTemplate;



    @MessageMapping("/send")
    public ChatMessageRequestDTO message(@RequestBody ChatMessageRequestDTO request) {

        ChatRoom chatRoom = chatRoomService.findById(request.getChatRoomId());

        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        // 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now(seoulZoneId);
        chatRoom.setUpdatedAt(now);

        ChatMessage chatMessage = ChatConverter.toChatMessage(request, chatRoom);

        chatMessageService.ChatMessageSave(chatMessage);

       // ChatMessageResponseDTO chatMessageResponseDTO = ChatConverter.toChatMessageResponseDTO(chatMessage);

        simpMessagingTemplate.convertAndSendToUser(request.getChatRoomId().toString(),"/private",request); // "/user/7/private" 7은 String임
        log.info("메시지를 전송했습니다: {}", request);

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
