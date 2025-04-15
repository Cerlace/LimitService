package cerlace.limitservice.web.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleAllExceptions(Exception ex) {
        HttpStatus status = determineHttpStatus(ex);
        return ResponseEntity.status(status).build();
    }

    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof org.springframework.http.converter.HttpMessageNotReadableException) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
