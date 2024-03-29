package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.AddressDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateAddressDto;
import hu.gyarmati.kemarkiexercise.exceptionhandling.AddressNotFoundByIdException;
import hu.gyarmati.kemarkiexercise.exceptionhandling.PersonAlreadyHasThatAddressTypeException;
import hu.gyarmati.kemarkiexercise.exceptionhandling.PersonAlreadyHasTwoAddressOrAddressTypeAlreadyInUseException;
import hu.gyarmati.kemarkiexercise.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Person person = personService.findPersonById(saveAndUpdateAddressDto.getPersonId());
        if (personService.checkPersonByAddressTypeAndNumberOfAddressType(AddressType.valueOf(saveAndUpdateAddressDto.getAddressType()), person.getId())) {
            throw new PersonAlreadyHasTwoAddressOrAddressTypeAlreadyInUseException();
        }
        Address address = modelMapper.map(saveAndUpdateAddressDto, Address.class);
        address.setPerson(person);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressInfoDto.class);
    }

    @Override
    public AddressDetailsDto getAddressById(Long id) {
        Address savedAddress = findAddressById(id);
        return modelMapper.map(savedAddress, AddressDetailsDto.class);
    }

    @Override
    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundByIdException(id));
    }

    @Override
    public List<AddressDetailsDto> getAllAddress() {
        return addressRepository.findAll().stream()
                .map(address -> modelMapper.map(address, AddressDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressInfoDto updateAddress(Long id, SaveAndUpdateAddressDto saveAndUpdateAddressDto) {
        Address existingAddress = findAddressById(id);

        if (existingAddress.getAddressType() != AddressType.valueOf(saveAndUpdateAddressDto.getAddressType())) {
            if (addressRepository.existsByAddressTypeAndPersonId(AddressType.valueOf(saveAndUpdateAddressDto.getAddressType()), saveAndUpdateAddressDto.getPersonId())) {
                throw new PersonAlreadyHasThatAddressTypeException(saveAndUpdateAddressDto.getAddressType());
            }
        }
        existingAddress.setAddressType(AddressType.valueOf(saveAndUpdateAddressDto.getAddressType()));
        return modelMapper.map(existingAddress, AddressInfoDto.class);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = findAddressById(id);
        address.setPerson(null);
        addressRepository.delete(address);
    }
}
