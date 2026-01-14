package dev.vorstu.coworkingapp.exceptions;

import dev.vorstu.coworkingapp.exceptions.accessdenied.AccessDeniedException;
import dev.vorstu.coworkingapp.exceptions.alreadyexist.AlreadyExistException;
import dev.vorstu.coworkingapp.exceptions.notfound.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundExceptionHandler(NotFoundException notFoundException) {
        log.error(notFoundException.getMessage(), notFoundException);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(notFoundException.getMessage()));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorMessage> alreadyExistExceptionHandler(AlreadyExistException alreadyExistException) {
        log.error(alreadyExistException.getMessage(), alreadyExistException);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(alreadyExistException.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> accessDeniedExceptionHandler(AccessDeniedException accessDeniedException) {
        log.error(accessDeniedException.getMessage(), accessDeniedException);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage(accessDeniedException.getMessage()));
    }
}
