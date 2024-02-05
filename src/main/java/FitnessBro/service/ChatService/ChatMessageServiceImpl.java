package FitnessBro.service.ChatService;


import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.respository.ChatMessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

    private final ChatMessageRepository chatMessageRepository;


    @Override
    @Transactional
    public List<ChatMessage> findAllChatByRoomId(Long roomId){
        return chatMessageRepository.findAllByRoomId(roomId);
    }


    @Override
    @Transactional
    public List<ChatMessage> findChatMessagesWithPaging(int page, Long roomId) {

        int pagePerCount = 15;

        Sort sort = Sort.by("createdAt").ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, pagePerCount, sort);

        List<ChatMessage> result = chatMessageRepository.findListsByRoomId(roomId, pageRequest).getContent();

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
