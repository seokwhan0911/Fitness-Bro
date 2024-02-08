package FitnessBro.domain;

import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Coach extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "coach_id")
    private Long id;

    private String name;
    @Setter
    private String nickname;

    private String email;

    private String password;

    private int age;

    private float rating;

    private String address;
    @Setter
    private String comment;     // 한 줄 인사말
    @Setter
    private int price;
    @Setter
    private String schedule;
    @Setter
    private String introduction;    // 선생님 소개




}
