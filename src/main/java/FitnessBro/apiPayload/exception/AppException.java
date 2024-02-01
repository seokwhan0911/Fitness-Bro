package FitnessBro.apiPayload.exception;


import FitnessBro.apiPayload.code.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{

    private ErrorStatus errorStatus;

    String message;

}
