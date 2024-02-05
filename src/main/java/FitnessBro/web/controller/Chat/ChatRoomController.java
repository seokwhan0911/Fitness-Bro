package FitnessBro.web.controller.Chat;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.ChatConverter;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.service.ChatService.ChatService;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.web.dto.Chat.ChatRoomRequestDTO;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatService chatService;
    private final MemberCommandService memberCommandService;
    private final CoachService coachService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    // 채팅방 생성 : memberId와 coachId로 채팅방 생성 후 채팅방 id, 생성 완료 메세지 리턴
    // /pub/connect 엔드포인트로 채팅하기 누를시.
    @MessageMapping("/connect")
    @SendTo("/topic/{roomId}") // 여기를 구독하고 있어야 함
    public ResponseEntity<ApiResponse<ChatRoomResponseDTO.ChatRoomCreateResponseDTO>> createRoom(@RequestParam @Valid ChatRoomRequestDTO request) {

        ChatRoom chatRoom = chatService.createRoom(request.getRoomId(), request.getMemberId(), request.getCoachId());
        ApiResponse<ChatRoomResponseDTO.ChatRoomCreateResponseDTO> apiResponse = ApiResponse.onSuccess(ChatConverter.toChatRoomCreateResponseDTO(chatRoom));
        return ResponseEntity.ok().body(apiResponse);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }
}
