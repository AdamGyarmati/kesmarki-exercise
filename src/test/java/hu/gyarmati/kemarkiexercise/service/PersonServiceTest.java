package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SavePersonDto;
import hu.gyarmati.kemarkiexercise.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PersonServiceImp personServiceImp;

    private Person person;
    private SavePersonDto savePersonDto;
    private PersonInfoDto personInfoDto;
    private PersonDetailsDto personDetailsDto;

    @BeforeEach
    public void setup() {
        person = Person.builder()
                .id(1L)
                .name("John Doe")
                .addressList(Collections.emptyList())
                .build();

        savePersonDto = SavePersonDto.builder()
                .name("John Doe")
                .build();

        personInfoDto = PersonInfoDto.builder()
                .id(1L)
                .name("John Doe")
                .build();

        personDetailsDto = PersonDetailsDto.builder()
                .id(1L)
                .name("John Doe")
                .addressList(Collections.emptyList())
                .build();
    }

    @DisplayName("Test for savePerson method")
    @Test
    public void canSavePerson() {
        doReturn(person).when(modelMapper).map(savePersonDto, Person.class);
        doReturn(personInfoDto).when(modelMapper).map(person, PersonInfoDto.class);

        given(personRepository.save(person)).willReturn(person);

        PersonInfoDto savedPerson = personServiceImp.savePerson(savePersonDto);

        assertThat(savedPerson).isNotNull();
    }

    @DisplayName("Test for getPersonById method")
    @Test
    public void canGetPersonById() {
        doReturn(personDetailsDto).when(modelMapper).map(person, PersonDetailsDto.class);

        given(personRepository.findById(1L)).willReturn(Optional.ofNullable(person));

        PersonDetailsDto getPersonDetailsDto = personServiceImp.getPersonById(person.getId());

        assertThat(getPersonDetailsDto).isNotNull();
    }
}
