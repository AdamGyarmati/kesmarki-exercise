package hu.gyarmati.kemarkiexercise.controller;

import hu.gyarmati.kemarkiexercise.dto.PersonDetailsDto;
import hu.gyarmati.kemarkiexercise.dto.PersonInfoDto;
import hu.gyarmati.kemarkiexercise.dto.SaveAndUpdatePersonDto;
import hu.gyarmati.kemarkiexercise.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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
    public ResponseEntity<PersonInfoDto> savePerson(@RequestBody SaveAndUpdatePersonDto saveAndUpdatePersonDto) {
        log.info("Http request POST /api/persons with body: " + saveAndUpdatePersonDto.toString());
        PersonInfoDto personInfoDto = personService.savePerson(saveAndUpdatePersonDto);
        return new ResponseEntity<>(personInfoDto, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDetailsDto> getPersonById(@PathVariable Long id) {
        log.info("Http request GET /api/persons/{id} with path variable: " + id);
        PersonDetailsDto personDetailsDto = personService.getPersonById(id);
        return new ResponseEntity<>(personDetailsDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonDetailsDto>> getAllPerson() {
        log.info("Http request GET /api/persons/all");
        List<PersonDetailsDto> personDetailsDtoList = personService.getAllPerson();
        return new ResponseEntity<>(personDetailsDtoList, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonInfoDto> updatePerson(@PathVariable Long id, @RequestBody SaveAndUpdatePersonDto saveAndUpdatePersonDto) {
        log.info("Http request PUT /api/persons with path variable: " + id + ", and body: " + saveAndUpdatePersonDto.toString());
        PersonInfoDto personInfoDto = personService.updatePerson(id, saveAndUpdatePersonDto);
        return new ResponseEntity<>(personInfoDto, OK);
    }
}
