package FitnessBro.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    MEMBERID_DUPLICATED(HttpStatus.CONFLICT, ""),
    MEMBERID_NOT_FOUND(HttpStatus.NOT_FOUND,""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"");
    private HttpStatus httpStatus;
    private String message;
}
