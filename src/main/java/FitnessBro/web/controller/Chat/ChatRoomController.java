package FitnessBro.web.controller.Chat;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.ChatConverter;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.service.ChatService.ChatMessageService;
import FitnessBro.service.ChatService.ChatRoomService;
import FitnessBro.service.LoginService.LoginService;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final LoginService loginService;


    @GetMapping("/members/chatrooms")
    @Operation(summary = "멤버의 채팅방 목록 보여주기", description = "멤버의 채팅리스트 보여주기 api")
    public ResponseEntity<ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>>> memberChatRoomList(@RequestHeader(value = "token") String token) {

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        List<ChatRoom> chatRoomsList = chatRoomService.findAllChatRoomListByMemberId(userId);
        chatRoomService.setLastChatMessage(chatRoomsList);
        List<ChatRoomResponseDTO.ChatRoomSimpleDTO> chatRoomSimpleDTOList = ChatConverter.toMemberChatRoomSimpleListDTO(chatRoomsList);
        chatMessageService.sortChatMessageDTO(chatRoomSimpleDTOList);

        ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>> apiResponse = ApiResponse.onSuccess(chatRoomSimpleDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/coaches/chatrooms")
    @Operation(summary = "코치의 채팅방 목록 보여주기", description = "코치의 채팅리스트 보여주기 api")
    public ResponseEntity<ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>>> coachChatRoomList(@RequestHeader(value = "token") String token) {

        String userEmail = loginService.decodeJwt(token);
        Long userId = loginService.getIdByEmail(userEmail);

        List<ChatRoom> chatRoomsList = chatRoomService.findAllChatRoomListByCoachId(userId);
        chatRoomService.setLastChatMessage(chatRoomsList);
        List<ChatRoomResponseDTO.ChatRoomSimpleDTO> chatRoomSimpleDTOList = ChatConverter.toCoachChatRoomSimpleListDTO(chatRoomsList);
        chatMessageService.sortChatMessageDTO(chatRoomSimpleDTOList);

        ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>> apiResponse = ApiResponse.onSuccess(chatRoomSimpleDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }




    // 채팅방 입장 화면
//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chat/roomdetail";
//    }

}