package FitnessBro.domain.favorites.Entity;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.common.BaseEntity;
import FitnessBro.domain.user.Entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Favorites extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "favorites_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;
}
