package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateAddressDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdatePersonDto;
import hu.gyarmati.kemarkiexercise.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AddressServiceImp addressServiceImp;

    private Address address;
    private SaveAndUpdateAddressDto saveAndUpdateAddressDto;
    private AddressInfoDto addressInfoDto;
    private Person person;

    @BeforeEach
    public void setup() {
        person = Person.builder()
                .id(1L)
                .name("John Doe")
                .addressList(Collections.emptyList())
                .build();

        address = Address.builder()
                .id(1L)
                .addressType(AddressType.PERMANENT)
                .person(person)
                .contactInformationList(Collections.emptyList())
                .build();

        saveAndUpdateAddressDto = SaveAndUpdateAddressDto.builder()
                .addressType("PERMANENT")
                .personId(person.getId())
                .build();

        addressInfoDto = AddressInfoDto.builder()
                .id(1L)
                .addressType("PERMANENT")
                .build();
    }


}
