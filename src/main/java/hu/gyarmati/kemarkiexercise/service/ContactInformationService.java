package hu.gyarmati.kemarkiexercise.service;


import hu.gyarmati.kemarkiexercise.dto.ContactInformationInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateContactInformationDto;

public interface ContactInformationService {
    ContactInformationInfoDto saveAddress(SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto);

    ContactInformationInfoDto getContactInformationById(Long id);
}
