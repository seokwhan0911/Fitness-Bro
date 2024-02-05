package FitnessBro.domain.Chat;


import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ChatMessage {


    @Id
    @GeneratedValue
    private Long id;

    //보내는 사람
    private String sender;
    //내용
    private String message;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
}
