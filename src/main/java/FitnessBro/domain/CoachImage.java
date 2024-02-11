package FitnessBro.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CoachImage {   // 동네형 사진첩

    @Id
    @GeneratedValue
    @Column(name = "coach_image_id")
    private Long id;

    private String url;

    @JoinColumn(name = "coach_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Coach coach;

}
