package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.ContactInformation;
import hu.gyarmati.kemarkiexercise.domain.ContactInformationType;
import hu.gyarmati.kemarkiexercise.dto.ContactInformationInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateContactInformationDto;
import hu.gyarmati.kemarkiexercise.exceptionhandling.ContactInformationNotFoundByIdException;
import hu.gyarmati.kemarkiexercise.repository.ContactInformationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactInformationServiceImp implements ContactInformationService {
    private final ContactInformationRepository contactInformationRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactInformationServiceImp(ContactInformationRepository contactInformationRepository, AddressService addressService, ModelMapper modelMapper) {
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

    @Override
    public ContactInformationInfoDto getContactInformationById(Long id) {
        ContactInformation contactInformation = findContactInformationById(id);
        return modelMapper.map(contactInformation, ContactInformationInfoDto.class);
    }

    @Override
    public List<ContactInformationInfoDto> getAllContactInformation() {
        return contactInformationRepository.findAll().stream()
                .map(info -> modelMapper.map(info, ContactInformationInfoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContactInformationInfoDto updateContactInformation(Long id, SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto) {
        Address address = addressService.findAddressById(saveAndUpdateContactInformationDto.getAddressId());
        ContactInformation contactInformation = findContactInformationById(id);
        contactInformation.setContactInfo(saveAndUpdateContactInformationDto.getContactInfo());
        contactInformation.setContactInformationType(ContactInformationType.valueOf(saveAndUpdateContactInformationDto.getContactInformationType()));
        contactInformation.setAddress(address);
        return modelMapper.map(contactInformation, ContactInformationInfoDto.class);
    }

    @Override
    public void deleteContactInformation(Long id) {
        ContactInformation contactInformation = findContactInformationById(id);
        contactInformation.setAddress(null);
        contactInformationRepository.delete(contactInformation);
    }

    private ContactInformation findContactInformationById(Long id) {
        return contactInformationRepository.findById(id).orElseThrow(() -> new ContactInformationNotFoundByIdException(id));
    }
}
