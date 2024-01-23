package FitnessBro.domain.register.Entity;

import FitnessBro.domain.coach.Entity.Coach;
import FitnessBro.domain.common.BaseEntity;
import FitnessBro.domain.user.Entity.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
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
    private Users user;

    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;


}
