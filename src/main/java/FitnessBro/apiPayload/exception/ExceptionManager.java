package FitnessBro.apiPayload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> apptimeExceptionHandler(AppException e){
        return ResponseEntity.status(e.getErrorStatus().getHttpStatus())
                .body(e.getErrorStatus().name() + " " + e.getMessage());
    }
}
