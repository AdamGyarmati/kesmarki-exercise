package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdatePersonDto;

import java.util.List;

public interface PersonService {
    PersonInfoDto savePerson(SaveAndUpdatePersonDto saveAndUpdatePersonDto);

    PersonDetailsDto getPersonById(Long id);

    List<PersonDetailsDto> getAllPerson();

    PersonInfoDto updatePerson(Long id, SaveAndUpdatePersonDto saveAndUpdatePersonDto);

    void deletePerson(Long id);

    Person findPersonById(Long id);
    Person checkPersonByAddressTypeAndNumberOfAddressType(AddressType addressType, Long personId);

    }
