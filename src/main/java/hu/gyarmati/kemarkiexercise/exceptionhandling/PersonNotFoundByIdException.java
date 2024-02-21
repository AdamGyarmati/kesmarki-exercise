package hu.gyarmati.kemarkiexercise.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonNotFoundByIdException extends RuntimeException {
    private Long personId;
}
