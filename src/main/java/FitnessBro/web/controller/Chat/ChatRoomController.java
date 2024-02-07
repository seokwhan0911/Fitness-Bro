package FitnessBro.web.controller.Chat;


import FitnessBro.apiPayload.ApiResponse;
import FitnessBro.converter.ChatConverter;
import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.respository.ChatRoomRepository;
import FitnessBro.service.ChatService.ChatMessageService;
import FitnessBro.service.ChatService.ChatRoomService;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.MemberService.MemberCommandService;
import FitnessBro.web.dto.Chat.ChatMessageDTO;
import FitnessBro.web.dto.Chat.ChatRoomRequestDTO;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.awt.SystemColor.info;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final MemberCommandService memberCommandService;
    private final CoachService coachService;
    private final ChatMessageService chatMessageService;

//     채팅 리스트 화면
//@GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }
//
//    모든 채팅방 목록 반환
    @GetMapping("/members/chatrooms/{memberId}")
    @Operation(summary = "멤버의 채팅방 목록 보여주기", description = "멤버의 채팅리스트 보여주기 api")
    public ResponseEntity<ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>>> memberChatRoomList(@PathVariable(value = "memberId") Long memberId) {

        List<ChatRoom> chatRoomsList = chatRoomService.findAllChatRoomListByMemberId(memberId);
        chatRoomService.setLastChatMessage(chatRoomsList);
        ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>> apiResponse = ApiResponse.onSuccess(ChatConverter.toMemberChatRoomSimpleListDTO(chatRoomsList));

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/coaches/chatrooms/{coachId}")
    @Operation(summary = "코치의 채팅방 목록 보여주기", description = "코치의 채팅리스트 보여주기 api")
    public ResponseEntity<ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>>> coachChatRoomList(@PathVariable(value = "coachId") Long coachId) {

        List<ChatRoom> chatRoomsList = chatRoomService.findAllChatRoomListByCoachId(coachId);
        chatRoomService.setLastChatMessage(chatRoomsList);
        ApiResponse<List<ChatRoomResponseDTO.ChatRoomSimpleDTO>> apiResponse = ApiResponse.onSuccess(ChatConverter.toCoachChatRoomSimpleListDTO(chatRoomsList));

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    // 채팅방 생성 : memberId와 coachId로 채팅방 생성 후 채팅방 id, 생성 완료 메세지 리턴
    // /pub/connect 엔드포인트로 채팅하기 누를시.
    @MessageMapping("/connect")
    @SendTo("/topic/{memberId}/{coachId}") // 여기를 구독하고 있어야 함
    public ResponseEntity<ApiResponse<ChatRoomResponseDTO.ChatRoomInfoDTO>> createRoom(@RequestBody @Valid ChatRoomRequestDTO request) {

        ChatRoom newChatRoom = new ChatRoom();
        ChatRoom chatRoom = chatRoomService.findChatRoomByMemberIdAndCoachId(request.getMemberId(),request.getCoachId());

        if(chatRoom == null){
            chatRoom = chatRoomService.createRoom(newChatRoom.getId(), request.getMemberId(), request.getCoachId());
        }

        List<ChatMessage> latestChatMessages = chatMessageService.findChatMessagesWithPaging(1, chatRoom.getId());

        List<ChatMessageDTO> chatMessageDTOList = ChatConverter.toChatMessageListDTO(latestChatMessages);

        ChatRoomResponseDTO.ChatRoomInfoDTO chatRoomInfoDto = ChatConverter.toChatRoomInfoDTO(chatRoom, chatMessageDTOList);
        ApiResponse<ChatRoomResponseDTO.ChatRoomInfoDTO> apiResponse = ApiResponse.onSuccess(chatRoomInfoDto);

        return ResponseEntity.ok().body(apiResponse);

    }

    @MessageMapping("/send")
    @SendTo("topic/chat/{roomId}")
    //전체경로는 "/sub/topic/chat/{roomId}이다.
    public ChatMessageDTO message(@RequestBody ChatMessageDTO message) {


        ChatRoom chatRoom = chatRoomService.findById(message.getRoomId());

        ChatMessage chatMessage = ChatConverter.toChatMessage(message, chatRoom);

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