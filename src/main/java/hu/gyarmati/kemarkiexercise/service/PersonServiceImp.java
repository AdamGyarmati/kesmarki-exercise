package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SavePersonDto;
import hu.gyarmati.kemarkiexercise.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImp implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonInfoDto savePerson(SavePersonDto savePersonDto) {
        Person savedperson = personRepository.save(modelMapper.map(savePersonDto, Person.class));
        return modelMapper.map(savedperson, PersonInfoDto.class);
    }

    @Override
    public PersonDetailsDto getPersonById(Long id) {
        return null;
    }
}
