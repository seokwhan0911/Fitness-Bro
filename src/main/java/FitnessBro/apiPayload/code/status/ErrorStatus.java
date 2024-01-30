package FitnessBro.apiPayload.code.status;

import FitnessBro.apiPayload.code.BaseErrorCode;
import FitnessBro.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 테스트용
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 코치 관련 에러

    COACH_NOT_FOUND(HttpStatus.BAD_REQUEST, "COACH4001", "코치가 없습니다"),
    MYPAGE_ERROR(HttpStatus.BAD_REQUEST, "COACH5002", "서버가 응답하지 않아요"),


    // 유저 관련 에러
    MEMBERID_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "사용자가 없습니다."),
    MEMBER_LOGIN_FAILURE(HttpStatus.BAD_REQUEST, "MEMBER4003", "아이디 혹은 비밀번호를 잘못 입력하였습니다."),
    MEMBERID_DUPLICATED(HttpStatus.CONFLICT, "MEEMBER4004", "중복된 아이디 입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"MEMBER4005", " 패스워드가 잘못 되었습니다."),


    LOCATION_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "MEMBER4003" , "동네 인증 argument가 잘못되었습니다."),

    // 회원가입 관련 에러
    MEMBER_SIGNUP_ERROR(HttpStatus.BAD_REQUEST, "SIGNUP4001", "회원가입 유효성 검사 실패"),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "SIGNUP4003", "이미 존재하는 이메일입니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }

}
