package FitnessBro.web.dto.Chat;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
            private String senderName;
            private String partnerName;
            private String lastChatMessage;
            private String pictureUrl;
    }

    @Builder
    @Getter
    @Setter
    public static class ChatRoomSimpleDTO{
        private Long chatRoomId;
        private String partnerName;
        List<ChatMessageDTO> chatMessageDTOList;
        private String lastChatMessage;
        private LocalDateTime lastChatTime;
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
