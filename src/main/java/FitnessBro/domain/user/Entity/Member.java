package FitnessBro.domain.user.Entity;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private int age;

//    private List<Image> image = new ArrayList<>();     // 추후에 이미지 엔티티 생성 예정
}
