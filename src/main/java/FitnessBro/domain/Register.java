package FitnessBro.domain;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Register extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "register_id")
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;

    private LocalDateTime createdAt;

    private Boolean  memberSuccess = false;

    private Boolean coachSuccess = false;

    @Enumerated(EnumType.STRING)
    private RegisterStatus registerStatus = RegisterStatus.UNSUCCESS;
}
