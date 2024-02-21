package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateAddressDto;
import hu.gyarmati.kemarkiexercise.exceptionhandling.PersonAlreadyHasTwoAddressOrAddressTypeAlreadyInUseException;
import hu.gyarmati.kemarkiexercise.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImp implements AddressService {
    private final AddressRepository addressRepository;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImp(AddressRepository addressRepository, PersonService personService, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressInfoDto saveAddress(SaveAndUpdateAddressDto saveAndUpdateAddressDto) {
        Person person = personService.checkPersonByAddressTypeAndNumberOfAddressType(AddressType.valueOf(saveAndUpdateAddressDto.getAddressType()), saveAndUpdateAddressDto.getPersonId());
        if (person == null) {
            throw new PersonAlreadyHasTwoAddressOrAddressTypeAlreadyInUseException();
        }
        Address address = modelMapper.map(saveAndUpdateAddressDto, Address.class);
        address.setPerson(person);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressInfoDto.class);
    }
}
