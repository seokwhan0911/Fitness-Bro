package FitnessBro.service.ChatService;


import FitnessBro.converter.ChatConverter;
import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.domain.Chat.ChatRoom;
import FitnessBro.respository.ChatMessageRepository;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

    private final ChatMessageRepository chatMessageRepository;


    @Override
    @Transactional
    public List<ChatMessage> findAllByChatRoomId(Long roomId){
        return chatMessageRepository.findAllByChatRoomId(roomId);
    }


    @Override
    @Transactional
    public List<ChatMessage> findChatMessagesWithPaging(int page, Long roomId) {

        int pagePerCount = 15;

        Sort sort = Sort.by("createdAt").ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, pagePerCount, sort);

        List<ChatMessage> result = chatMessageRepository.findListsByChatRoomId(roomId, pageRequest).getContent();

        return result;
    }
    @Override
    @Transactional
    public ChatMessage findMessageByRoomId(Long roomId){
        return chatMessageRepository.findByChatRoomId(roomId);
    }

    @Override
    @Transactional
    public void ChatMessageSave(ChatMessage chatMessage){
        chatMessageRepository.save(chatMessage);
    }




}
