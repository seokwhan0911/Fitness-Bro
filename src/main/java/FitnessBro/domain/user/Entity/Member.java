package FitnessBro.domain.user.Entity;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@DynamicUpdate  // Update시 null인 경우 쿼리를 안 보냄
//@DynamicInsert  // Insert시 null인 경우 쿼리를 안 보냄
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