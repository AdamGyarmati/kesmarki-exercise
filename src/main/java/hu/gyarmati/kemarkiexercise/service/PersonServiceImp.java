package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdatePersonDto;
import hu.gyarmati.kemarkiexercise.exceptionhandling.PersonNotFoundByIdException;
import hu.gyarmati.kemarkiexercise.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public PersonInfoDto savePerson(SaveAndUpdatePersonDto saveAndUpdatePersonDto) {
        Person savedperson = personRepository.save(modelMapper.map(saveAndUpdatePersonDto, Person.class));
        return modelMapper.map(savedperson, PersonInfoDto.class);
    }

    @Override
    public PersonDetailsDto getPersonById(Long id) {
        Person person = findPersonById(id);
        return modelMapper.map(person, PersonDetailsDto.class);
    }

    @Override
    public List<PersonDetailsDto> getAllPerson() {
        return personRepository.findAll().stream()
                .map(person -> modelMapper.map(person, PersonDetailsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonInfoDto updatePerson(Long id, SaveAndUpdatePersonDto saveAndUpdatePersonDto) {
        Person person = findPersonById(id);
        person.setName(saveAndUpdatePersonDto.getName());
        return modelMapper.map(person, PersonInfoDto.class);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = findPersonById(id);
        person.getAddressList().forEach(address -> address.setPerson(null));
        personRepository.delete(person);
    }

    @Override
    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundByIdException(id));
    }

    @Override
    public boolean checkPersonByAddressTypeAndNumberOfAddressType(AddressType addressType, Long personId) {
        return personRepository.checkPersonByAddressTypeAndNumberOfAddressType(addressType, personId);
    }
}
