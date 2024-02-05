package FitnessBro.web.dto.Chat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessageDTO {

    private String roomId;
    private String writer;
    private String message;
}
