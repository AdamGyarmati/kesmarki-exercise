package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.dto.AddressDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateAddressDto;

import java.util.List;

public interface AddressService {
    AddressInfoDto saveAddress(SaveAndUpdateAddressDto saveAndUpdateAddressDto);

    AddressDetailsDto getAddressById(Long id);
    Address findAddressById(Long id);

    List<AddressDetailsDto> getAllAddress();
}
