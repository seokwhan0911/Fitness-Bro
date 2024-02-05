package FitnessBro.service.ChatService;

import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.member.Entity.Member;
import FitnessBro.respository.ChatRoomRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRoomRepository chatRoomRepository;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    @Override
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    @Override
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    @Override
    public ChatRoom createRoom(Member member, Coach coach) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
