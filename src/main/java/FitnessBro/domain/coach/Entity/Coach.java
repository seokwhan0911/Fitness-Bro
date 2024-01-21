package FitnessBro.domain.coach.Entity;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

    private String address;

    private String comment;

    private int price;

    private String schedule;

    private String introduction;

    // 추후에 PM 기획 후 추가 예정
}
