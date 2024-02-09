package FitnessBro.domain;

import FitnessBro.apiPayload.Utill.StringUtils;
import FitnessBro.domain.common.BaseEntity;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Member.MemberRequestDTO;
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

    @Setter
    private String address;     // 회원 거주 지역

    @Setter
    private String pictureURL;      // 회원 사진 URL

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

    public void update(MemberRequestDTO.MemberUpdateRequestDTO memberUpdateRequestDTO) {
        if (StringUtils.isNotBlank(memberUpdateRequestDTO.getNickname())) {
            this.nickname = memberUpdateRequestDTO.getNickname();
        }
        if (StringUtils.isNotBlank(memberUpdateRequestDTO.getEmail())) {
            this.email = memberUpdateRequestDTO.getEmail();
        }
        if (StringUtils.isNotBlank(memberUpdateRequestDTO.getPassword())) {
            this.password = memberUpdateRequestDTO.getPassword();
        }
    }

}