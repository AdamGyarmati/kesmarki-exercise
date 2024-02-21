package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdatePersonDto;
import hu.gyarmati.kemarkiexercise.exceptionhandling.PersonNotFoundByIdException;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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
    private SaveAndUpdatePersonDto saveAndUpdatePersonDto;
    private PersonInfoDto personInfoDto;
    private PersonDetailsDto personDetailsDto;

    @BeforeEach
    public void setup() {
        person = Person.builder()
                .id(1L)
                .name("John Doe")
                .addressList(Collections.emptyList())
                .build();

        saveAndUpdatePersonDto = SaveAndUpdatePersonDto.builder()
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
        doReturn(person).when(modelMapper).map(saveAndUpdatePersonDto, Person.class);
        doReturn(personInfoDto).when(modelMapper).map(person, PersonInfoDto.class);

        given(personRepository.save(person)).willReturn(person);

        PersonInfoDto savedPerson = personServiceImp.savePerson(saveAndUpdatePersonDto);

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

    @DisplayName("Test for getPersonById method throw PersonNotFoundException")
    @Test
    public void canGetPersonById_throw_PersonNotFoundException() {
        given(personRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(PersonNotFoundByIdException.class, () -> personServiceImp.getPersonById(1L));
    }

    @DisplayName("Test for getAllPerson")
    @Test
    public void canGetAllPerson() {
        doReturn(personDetailsDto).when(modelMapper).map(person, PersonDetailsDto.class);

        given(personRepository.findAll()).willReturn(List.of(person, new Person(2L, "Jane Doe", Collections.emptyList())));

        List<PersonDetailsDto> personDetailsDtoList = personServiceImp.getAllPerson();

        assertThat(personDetailsDtoList).hasSize(2);
    }

    @DisplayName("Test for updatePerson")
    @Test
    public void canUpdatePerson() {
        SaveAndUpdatePersonDto dto = new SaveAndUpdatePersonDto("Jane Doe");
        PersonInfoDto expectedPerson = new PersonInfoDto(1L, "Jane Doe");

        given(personRepository.findById(person.getId())).willReturn(Optional.ofNullable(person));
        given(modelMapper.map(person, PersonInfoDto.class)).willReturn(expectedPerson);

        PersonInfoDto updatedPerson = personServiceImp.updatePerson(person.getId(), dto);

        assertThat(updatedPerson).isEqualTo(expectedPerson);
    }
}
