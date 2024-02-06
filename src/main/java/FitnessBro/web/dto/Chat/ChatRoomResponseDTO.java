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
            private List<ChatMessageDTO> latestChatMessages;
            private LocalDateTime createdAt;
    }
}
