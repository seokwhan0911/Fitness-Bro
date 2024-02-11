package FitnessBro.web.dto.Chat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessageDTO {

    private Long roomId;
    private String sender;
    private String message;



}
