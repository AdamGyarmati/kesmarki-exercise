package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SavePersonDto;

import java.util.List;

public interface PersonService {
    PersonInfoDto savePerson(SavePersonDto savePersonDto);

    PersonDetailsDto getPersonById(Long id);

    List<PersonDetailsDto> getAllPerson();
}
