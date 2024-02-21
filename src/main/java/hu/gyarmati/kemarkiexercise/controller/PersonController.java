package hu.gyarmati.kemarkiexercise.controller;

import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SavePersonDto;
import hu.gyarmati.kemarkiexercise.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/persons")
@Slf4j
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonInfoDto> savePerson(@RequestBody SavePersonDto savePersonDto) {
        log.info("Http request POST /api/persons with body: " + savePersonDto.toString());
        PersonInfoDto personInfoDto = personService.savePerson(savePersonDto);
        return new ResponseEntity<>(personInfoDto, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDetailsDto> getPersonById(@PathVariable Long id) {
        return null;
    }
}
