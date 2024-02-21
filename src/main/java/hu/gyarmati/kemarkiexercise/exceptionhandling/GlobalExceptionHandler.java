package hu.gyarmati.kemarkiexercise.exceptionhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(PersonNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handlePersonNotFoundByIdException(PersonNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("personId", "Person not found by id: " + exception.getPersonId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
