package FitnessBro.web.dto.Chat;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ChatMessageResponseDTO {

    private Long id;
    //보내는 사람
    private String sender;
    //내용
    private String message;

    private LocalDateTime createdAt;
}
