package FitnessBro.apiPayload.code.status;

import FitnessBro.apiPayload.code.BaseCode;
import FitnessBro.apiPayload.code.BaseErrorCode;
import FitnessBro.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 읿반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    //로그인 관련 응답
    _LOGIN_SUCCESS(HttpStatus.OK, "LOGIN200", "로그인 성공입니다."),

    //회원가입 관련 응답
    _SIGNUP_SUCCESS(HttpStatus.OK, "SIGNUP200", "회원가입 성공입니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }
}
