package hu.gyarmati.kemarkiexercise.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressNotFoundByIdException extends RuntimeException {
    private Long addressId;
}

