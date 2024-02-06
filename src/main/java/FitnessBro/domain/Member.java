package FitnessBro.domain;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
//@DynamicUpdate  // Update시 null인 경우 쿼리를 안 보냄
//@DynamicInsert  // Insert시 null인 경우 쿼리를 안 보냄
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor

public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private int age;


//    private List<Image> image = new ArrayList<>();     // 추후에 이미지 엔티티 생성 예정
}