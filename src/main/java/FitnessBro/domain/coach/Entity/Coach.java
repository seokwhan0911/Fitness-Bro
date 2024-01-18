package FitnessBro.domain.coach.Entity;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Coach extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "coach_id")
    private Long id;

    private String name;

    private String nickname;

    private String email;

    private String password;

    private int age;

    private Long rating;

    // 추후에 PM 기획 후 추가 예정
}
