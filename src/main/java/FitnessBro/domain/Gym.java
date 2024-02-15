package FitnessBro.domain;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Gym extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "gym_id")
    private Long id;
    //소재지전화
    private String phoneNumber;
    //도로명전체주소
    private String address;
    //도로명우편번호
    private String postalCode;
    //사업장명
    private String name;
    //시
    private String region;
    //구
    private String subAddress;
    //도로명
    private String detailAddress;

}
