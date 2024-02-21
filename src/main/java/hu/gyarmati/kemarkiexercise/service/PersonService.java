package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SavePersonDto;

public interface PersonService {
    PersonInfoDto savePerson(SavePersonDto savePersonDto);
}
