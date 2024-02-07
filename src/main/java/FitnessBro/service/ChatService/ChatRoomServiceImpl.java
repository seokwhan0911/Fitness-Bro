package FitnessBro.service.ChatService;

import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;

import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;
import FitnessBro.respository.ChatRoomRepository;
import FitnessBro.service.CoachService.CoachService;
import FitnessBro.service.MemberService.MemberCommandService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final MemberCommandService memberCommandService;
    private final CoachService coachService;
    private final ChatRoomRepository chatRoomRepository;

//    @PostConstruct
//의존관게 주입완료되면 실행되는 코드
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }

    //채팅방 불러오기
//    @Override
//    public List<ChatRoom> findAllRoom() {
//        채팅방 최근 생성 순으로 반환
//       List<ChatRoom> result = new ArrayList<>(chatRooms.values());
//        Collections.reverse(result);
//
//        return result;
//    }
//
    //채팅방 하나 불러오기
    @Override
    @Transactional
    public ChatRoom findById(Long roomId) {

        return chatRoomRepository.findById(roomId).orElse(null);
    }

    //채팅방 생성
    @Override
    @Transactional
    public ChatRoom createRoom(Long roomId,Long memberId, Long coachId) {
        Member member = memberCommandService.getMemberById(memberId);
        Coach coach = coachService.getCoachById(coachId);
        ChatRoom chatRoom = ChatRoom.builder()
                .Id(roomId)
                .member(member)
                .coach(coach)
                .build();

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    @Override
    @Transactional
    public List<ChatRoom> findAllChatRoomListByMemberId(Long memberId){
        return chatRoomRepository.findAllByMemberId(memberId);
    }

    @Override
    @Transactional
    public List<ChatRoom> findAllChatRoomListByCoachId(Long coachId){
        return chatRoomRepository.findAllByCoachId(coachId);
    }


    @Override
    @Transactional
    public void setLastChatMessage(List<ChatRoom> chatRoomList)
    {
        for (ChatRoom chatRoom : chatRoomList) {
            List<ChatMessage> chatMessageList = chatRoom.getChatMessage();
            if (!chatMessageList.isEmpty()) {
                ChatMessage lastChatMessage = chatMessageList.get(chatMessageList.size() - 1);
                chatRoom.setLastChatMessage(lastChatMessage);
            }
        }
    }

}
