package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.ContactInformation;
import hu.gyarmati.kemarkiexercise.domain.ContactInformationType;
import hu.gyarmati.kemarkiexercise.dto.AddressDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.ContactInformationInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdateContactInformationDto;
import hu.gyarmati.kemarkiexercise.exceptionhandling.AddressNotFoundByIdException;
import hu.gyarmati.kemarkiexercise.exceptionhandling.ContactInformationNotFoundByIdException;
import hu.gyarmati.kemarkiexercise.repository.ContactInformationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ContactInformationServiceTest {

    @Mock
    private ContactInformationRepository contactInformationRepository;

    @Mock
    private AddressService addressService;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    private ContactInformationServiceImp contactInformationServiceImp;

    private ContactInformation contactInformation;

    private SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto;

    private ContactInformationInfoDto contactInformationInfoDto;

    private Address address;

    @BeforeEach
    public void setup() {
        contactInformation = ContactInformation.builder()
                .id(1L)
                .contactInfo("Info")
                .contactInformationType(ContactInformationType.EMAIL)
                .build();

        saveAndUpdateContactInformationDto = SaveAndUpdateContactInformationDto.builder()
                .contactInfo("Info")
                .contactInformationType("EMAIL")
                .addressId(1L)
                .build();

        contactInformationInfoDto = ContactInformationInfoDto.builder()
                .id(1L)
                .contactInfo("Info")
                .contactInformationType("EMAIL")
                .build();

        address = Address.builder()
                .id(1L)
                .addressType(AddressType.PERMANENT)
                .build();
    }

    @DisplayName("Test for saveContactInformation method")
    @Test
    public void canSaveContactInformation() {
        doReturn(contactInformation).when(modelMapper).map(saveAndUpdateContactInformationDto, ContactInformation.class);
        doReturn(contactInformationInfoDto).when(modelMapper).map(contactInformation, ContactInformationInfoDto.class);

        given(addressService.findAddressById(address.getId())).willReturn(address);

        given(contactInformationRepository.save(contactInformation)).willReturn(contactInformation);

        ContactInformationInfoDto contactInformationInfoDto = contactInformationServiceImp.saveAddress(saveAndUpdateContactInformationDto);

        assertThat(contactInformationInfoDto).isNotNull();
    }

    @DisplayName("Test for getContactInformationById method")
    @Test
    public void canGetContactInformationById() {
        doReturn(contactInformationInfoDto).when(modelMapper).map(contactInformation, ContactInformationInfoDto.class);

        given(contactInformationRepository.findById(1L)).willReturn(Optional.ofNullable(contactInformation));

        ContactInformationInfoDto getContactInformationInfoDto = contactInformationServiceImp.getContactInformationById(contactInformation.getId());

        assertThat(getContactInformationInfoDto).isNotNull();
    }

    @DisplayName("Test for getContactInformationById method throw ContactInformationNotFoundByIdException")
    @Test
    public void canGetContactInformationById_throw_ContactInformationNotFoundByIdException() {
        given(contactInformationRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ContactInformationNotFoundByIdException.class, () -> contactInformationServiceImp.getContactInformationById(1L));
    }
}
