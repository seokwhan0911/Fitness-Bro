package FitnessBro.domain.review.Entity;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.common.BaseEntity;
import FitnessBro.domain.member.Entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private LocalDateTime date;

    private String contents;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;
}
