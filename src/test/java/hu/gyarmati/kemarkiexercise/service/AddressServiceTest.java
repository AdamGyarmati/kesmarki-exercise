package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.AddressDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateAddressDto;
import hu.gyarmati.kemarkiexercise.exceptionhandling.AddressNotFoundByIdException;
import hu.gyarmati.kemarkiexercise.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PersonService personService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AddressServiceImp addressServiceImp;

    private Address address;
    private SaveAndUpdateAddressDto saveAndUpdateAddressDto;
    private AddressInfoDto addressInfoDto;
    private AddressDetailsDto addressDetailsDto;
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
                .addressType("OTHER")
                .personId(person.getId())
                .build();

        addressInfoDto = AddressInfoDto.builder()
                .id(1L)
                .addressType("PERMANENT")
                .build();

        addressDetailsDto = AddressDetailsDto.builder()
                .id(1L)
                .addressType("PERMANENT")
                .contactInformationList(Collections.emptyList())
                .build();
    }

    @DisplayName("Test for saveAddress method")
    @Test
    public void canSaveAddress() {
        doReturn(address).when(modelMapper).map(saveAndUpdateAddressDto, Address.class);
        doReturn(addressInfoDto).when(modelMapper).map(address, AddressInfoDto.class);

        given(personService.findPersonById(1L)).willReturn(person);
        given(personService.checkPersonByAddressTypeAndNumberOfAddressType(AddressType.OTHER, person.getId())).willReturn(false);

        given(addressRepository.save(address)).willReturn(address);

        AddressInfoDto savedAddress = addressServiceImp.saveAddress(saveAndUpdateAddressDto);

        assertThat(savedAddress).isNotNull();
    }

    @DisplayName("Test for getAddressById method")
    @Test
    public void canGetAddressById() {
        doReturn(addressDetailsDto).when(modelMapper).map(address, AddressDetailsDto.class);

        given(addressRepository.findById(1L)).willReturn(Optional.ofNullable(address));

        AddressDetailsDto getAddressDetailsDto = addressServiceImp.getAddressById(address.getId());

        assertThat(getAddressDetailsDto).isNotNull();
    }

    @DisplayName("Test for getAddressById method throw AddressNotFoundByIdException")
    @Test
    public void canGetAddressById_throw_AddressNotFoundByIdException() {
        given(addressRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(AddressNotFoundByIdException.class, () -> addressServiceImp.getAddressById(1L));
    }

    @DisplayName("Test for getAllAddress")
    @Test
    public void canGetAllAddress() {
        doReturn(addressDetailsDto).when(modelMapper).map(address, AddressDetailsDto.class);

        given(addressRepository.findAll()).willReturn(List.of(address, new Address(2L, AddressType.TEMPORARY, person, Collections.emptyList())));

        List<AddressDetailsDto> addressDetailsDtoList = addressServiceImp.getAllAddress();

        assertThat(addressDetailsDtoList).hasSize(2);
    }

    @DisplayName("Test for updateAddress")
    @Test
    public void canUpdateAddress() {
        SaveAndUpdateAddressDto dto = new SaveAndUpdateAddressDto("PERMANENT", 1L);
        AddressInfoDto expectedAddress = new AddressInfoDto(1L, "PERMANENT");

        given(addressRepository.findById(address.getId())).willReturn(Optional.ofNullable(address));
        given(modelMapper.map(address, AddressInfoDto.class)).willReturn(expectedAddress);

        AddressInfoDto updatedAddress = addressServiceImp.updateAddress(address.getId(), dto);

        assertThat(updatedAddress).isEqualTo(expectedAddress);
    }

    @DisplayName("Test for deleteAddress")
    @Test
    public void canDeleteAddress() {
        when(addressRepository.findById(address.getId())).thenReturn(Optional.ofNullable(address));

        addressServiceImp.deleteAddress(address.getId());

        verify(addressRepository, times(1)).delete(address);
    }
}
