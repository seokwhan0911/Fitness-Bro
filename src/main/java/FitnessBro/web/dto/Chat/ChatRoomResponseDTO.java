package FitnessBro.web.dto.Chat;


import FitnessBro.domain.Chat.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;


public class ChatRoomResponseDTO {

    @Builder
    @Getter
    public static class ChatRoomCreateResponseDTO{
        Long roomId;
        String message;
    }


    @Builder
    @Getter
    public static class ChatRoomInfoDTO{

            private Long chatRoomId;
            private List<ChatMessageDTO> latestChatMessages;
            private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class ChatRoomSimpleDTO{
        private Long chatRoomId;
        private String lastChatMessage;
        private LocalDateTime createdAt;
        private String partnerName;
    }
}
