package FitnessBro.web.controller.Chat;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.ChatConverter;
import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.respository.ChatRoomRepository;
import FitnessBro.service.ChatService.ChatMessageService;
import FitnessBro.service.ChatService.ChatRoomService;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.web.dto.Chat.ChatMessageDTO;
import FitnessBro.web.dto.Chat.ChatRoomRequestDTO;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.info;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final MemberCommandService memberCommandService;
    private final CoachService coachService;
    private final ChatMessageService chatMessageService;

    // 채팅 리스트 화면
//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }
//
//    모든 채팅방 목록 반환
//    @GetMapping("/rooms")
//    @ResponseBody
//    public List<ChatRoom> room() {
//        return chatRoomService.findAllRoom();
//    }

    // 채팅방 생성 : memberId와 coachId로 채팅방 생성 후 채팅방 id, 생성 완료 메세지 리턴
    // /pub/connect 엔드포인트로 채팅하기 누를시.
    @MessageMapping("/connect")
    @SendTo("/topic/{roomId}") // 여기를 구독하고 있어야 함
    public ResponseEntity<ApiResponse<ChatRoomResponseDTO.ChatRoomInfoDTO>> createRoom(@RequestBody @Valid ChatRoomRequestDTO request) {

        ChatRoom chatRoom = chatRoomService.createRoom(request.getRoomId(), request.getMemberId(), request.getCoachId());

        List<ChatMessage> latestChatMessages = chatMessageService.findChatMessagesWithPaging(1, request.getRoomId());

        List<ChatMessageDTO> chatMessageDtoList = new ArrayList<>();
        List<ChatMessageDTO> chatMessageDTOList = ChatConverter.toChatMessageListDTO(latestChatMessages);

        ChatRoomResponseDTO.ChatRoomInfoDTO chatRoomInfoDto = ChatConverter.toChatRoomInfoDTO(chatRoom, chatMessageDTOList);
        ApiResponse<ChatRoomResponseDTO.ChatRoomInfoDTO> apiResponse = ApiResponse.onSuccess(chatRoomInfoDto);

        return ResponseEntity.ok().body(apiResponse);

    }

    @MessageMapping("/send")
    @SendTo("topic/chat/{roomId}")
    //전체경로는 "/sub/topic/chat/{roomId}이다.
    public ChatMessageDTO message(@RequestBody ChatMessageDTO message) {


        ChatRoom chatroom = chatRoomService.findById(message.getRoomId());

        ChatMessage chatMessage = ChatConverter.toChatMessage(message, chatroom);

        chatMessageService.ChatMessageSave(chatMessage);

        return message;
    }

    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }
//    특정 채팅방 조회
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoom roomInfo(@PathVariable String roomId) {
//        return chatRoomService.findById(roomId);
//    }
}