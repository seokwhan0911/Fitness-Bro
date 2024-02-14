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

    private String address;
    private String region;
    private String sub_address;
    private String detail_address;
    private String name;
    private String phone_number;
    private String postal_code;

}
