package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.ContactInformation;
import hu.gyarmati.kemarkiexercise.dto.ContactInformationInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateContactInformationDto;
import hu.gyarmati.kemarkiexercise.repository.ContactInformationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactInformationImp implements ContactInformationService {
    private final ContactInformationRepository contactInformationRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactInformationImp(ContactInformationRepository contactInformationRepository, AddressService addressService, ModelMapper modelMapper) {
        this.contactInformationRepository = contactInformationRepository;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactInformationInfoDto saveAddress(SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto) {
        Address address = addressService.findAddressById(saveAndUpdateContactInformationDto.getAddressId());
        ContactInformation contactInformation = modelMapper.map(saveAndUpdateContactInformationDto, ContactInformation.class);
        contactInformation.setAddress(address);
        return modelMapper.map(contactInformationRepository.save(contactInformation), ContactInformationInfoDto.class);
    }
}
