package FitnessBro.domain;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class CoachGym extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "coach_gym_id")
    private Long id;

    @JoinColumn(name = "gym_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;

    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;
}
