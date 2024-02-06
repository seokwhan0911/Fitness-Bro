package FitnessBro.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Data
public class ReviewRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewDTO{

        @NotNull
        String coachNickname;

        @NotNull
        @Range(min = 0, max = 5)
        Float rating;

        @NotNull
        String contents;
    }

}
