package hu.gyarmati.kemarkiexercise.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactInformationNotFoundByIdException extends RuntimeException {
    private Long contactInformationId;
}
