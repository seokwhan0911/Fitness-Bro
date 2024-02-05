package FitnessBro.web.dto.Chat;

import lombok.Builder;
import lombok.Getter;


public class ChatRoomResponseDTO {

    @Builder
    @Getter
    public static class ChatRoomCreateResponseDTO{
        Long roomId;
        String message;
    }
}
