package FitnessBro.domain;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//@DynamicUpdate  // Update시 null인 경우 쿼리를 안 보냄
//@DynamicInsert  // Insert시 null인 경우 쿼리를 안 보냄
@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Setter
    private String nickname;

    @Setter
    private String email;

    @Setter
    private String password;

    @Setter
    private int age;

    @OneToMany(mappedBy = "member",
            orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    private List<Register> registers = new ArrayList<>();

    @OneToMany(mappedBy = "member",
            orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member",
            orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    private List<Favorites> favorites = new ArrayList<>();



//    private List<Image> image = new ArrayList<>();     // 추후에 이미지 엔티티 생성 예정
}