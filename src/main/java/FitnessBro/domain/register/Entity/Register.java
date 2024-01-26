package FitnessBro.domain.register.Entity;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.common.BaseEntity;
import FitnessBro.domain.user.Entity.Member;
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

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;

    private LocalDateTime createdAt;

    private Boolean  memberSuccess = false;

    private Boolean coachSuccess = false;
}
