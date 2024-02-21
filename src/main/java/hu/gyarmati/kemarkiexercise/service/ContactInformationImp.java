package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.dto.ContactInformationInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateContactInformationDto;
import hu.gyarmati.kemarkiexercise.repository.ContactInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactInformationImp implements ContactInformationService {
    private final ContactInformationRepository contactInformationRepository;

    @Autowired
    public ContactInformationImp(ContactInformationRepository contactInformationRepository) {
        this.contactInformationRepository = contactInformationRepository;
    }

    @Override
    public ContactInformationInfoDto saveAddress(SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto) {
        return null;
    }
}
