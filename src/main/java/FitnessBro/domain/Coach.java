package FitnessBro.domain;

import FitnessBro.apiPayload.Utill.StringUtils;
import FitnessBro.domain.common.BaseEntity;
import jakarta.persistence.*;
import FitnessBro.web.dto.Coach.CoachRequestDTO;
import FitnessBro.web.dto.Coach.CoachResponseDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Setter
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

    // 추후에 PM 기획 후 추가 예정
    public void update(CoachRequestDTO.CoachUpdateRequestDTO coachUpdateRequestDTO) {
        if(StringUtils.isNotBlank(coachUpdateRequestDTO.getNickname())){
            this.nickname = coachUpdateRequestDTO.getNickname();
        }
        if(StringUtils.isNotBlank(coachUpdateRequestDTO.getEmail())){
            this.email = coachUpdateRequestDTO.getEmail();
        }
        if(StringUtils.isNotBlank(coachUpdateRequestDTO.getPassword())){
            this.password = coachUpdateRequestDTO.getPassword();
        }
        if(StringUtils.isNotBlank(coachUpdateRequestDTO.getAddress())){
            this.address = coachUpdateRequestDTO.getAddress();
        }
        if(StringUtils.isNotBlank(coachUpdateRequestDTO.getComment())){
            this.comment = coachUpdateRequestDTO.getComment();
        }
        if(coachUpdateRequestDTO.getPrice() != 0){
            this.price = coachUpdateRequestDTO.getPrice();
        }
        if (StringUtils.isNotBlank(coachUpdateRequestDTO.getSchedule())) {
            this.schedule = coachUpdateRequestDTO.getSchedule();
        }
        if (StringUtils.isNotBlank(coachUpdateRequestDTO.getIntroduction())) {
            this.introduction = coachUpdateRequestDTO.getIntroduction();
        }
    }


}
