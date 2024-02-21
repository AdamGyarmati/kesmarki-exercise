package hu.gyarmati.kemarkiexercise.service;


import hu.gyarmati.kemarkiexercise.dto.ContactInformationInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateContactInformationDto;

import java.util.List;

public interface ContactInformationService {
    ContactInformationInfoDto saveAddress(SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto);

    ContactInformationInfoDto getContactInformationById(Long id);

    List<ContactInformationInfoDto> getAllContactInformation();

    ContactInformationInfoDto updateContactInformation(Long id, SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto);

    void deleteContactInformation(Long id);
}
