package FitnessBro.web.dto;

import com.google.common.annotations.Beta;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class RegisterResponseDTO {

    @Builder
    @Getter
    public static class RegisterMemberDTO{
        String nickname;
        LocalDateTime creatdAt;
    }
}
