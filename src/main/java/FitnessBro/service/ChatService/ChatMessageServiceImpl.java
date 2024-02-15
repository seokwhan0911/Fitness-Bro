package FitnessBro.service.ChatService;


import FitnessBro.domain.Chat.ChatMessage;
import FitnessBro.respository.ChatMessageRepository;
import FitnessBro.web.dto.Chat.ChatRoomResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


    @Override
    @Transactional
    public List<ChatRoomResponseDTO.ChatRoomSimpleDTO> sortChatMessageDTO(List<ChatRoomResponseDTO.ChatRoomSimpleDTO> chatRoomSimpleDTOList){
        // Comparator를 사용하여 createdAt 속성을 기준으로 정렬



        for(ChatRoomResponseDTO.ChatRoomSimpleDTO chatRoomSimpleDTO : chatRoomSimpleDTOList){

            Comparator<ChatRoomResponseDTO.ChatMessageDTO> comparator = Comparator.comparing(ChatRoomResponseDTO.ChatMessageDTO::getCreatedAt);
            List<ChatRoomResponseDTO.ChatMessageDTO> sortedMessages = chatRoomSimpleDTO.getChatMessageDTOList().stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
            chatRoomSimpleDTO.setChatMessageDTOList(sortedMessages);
        }
        return chatRoomSimpleDTOList;
    }

}
