package FitnessBro.domain.Chat;


import FitnessBro.domain.Coach;
import FitnessBro.domain.Member;

import FitnessBro.domain.common.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@AllArgsConstructor
@Builder
@Setter
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long Id;


    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessage = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private ChatMessage lastChatMessage;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;

}
