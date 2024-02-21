package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateAddressDto;

public interface AddressService {
    AddressInfoDto saveAddress(SaveAndUpdateAddressDto saveAndUpdateAddressDto);
}
