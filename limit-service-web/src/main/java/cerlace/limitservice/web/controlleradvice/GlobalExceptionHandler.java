package cerlace.limitservice.web.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик исключений для REST API.
 * <p>
 * Перехватывает исключения, возникающие в приложении, и преобразует их
 * в соответствующие HTTP-ответы.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает все не перехваченные исключения.
     *
     * @param ex исключение
     * @return ResponseEntity с соответствующим HTTP-статусом
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleAllExceptions(Exception ex) {
        HttpStatus status = determineHttpStatus(ex);
        return ResponseEntity.status(status).build();
    }

    /**
     * Определяет HTTP-статус на основе типа исключения.
     *
     * @param ex исключение
     * @return соответствующий HTTP-статус
     */
    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof org.springframework.http.converter.HttpMessageNotReadableException) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
