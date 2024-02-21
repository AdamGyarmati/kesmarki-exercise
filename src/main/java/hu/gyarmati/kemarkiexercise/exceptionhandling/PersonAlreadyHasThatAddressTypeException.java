package hu.gyarmati.kemarkiexercise.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonAlreadyHasThatAddressTypeException extends RuntimeException {
    String addressType;
}
