package FitnessBro.web.dto.Chat;


import lombok.Builder;
import lombok.Getter;

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
            private List<ChatMessageRequestDTO> latestChatMessages;
            private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class ChatRoomSimpleDTO{
        private Long chatRoomId;
        List<ChatMessageDTO> chatMessageDTOList;
        private String lastChatMessage;
        private String pictureUrl;
    }

    @Builder
    @Getter
    public static class ChatMessageDTO{
        private String sender;
        private String message;
        private LocalDateTime createdAt;
    }
}
