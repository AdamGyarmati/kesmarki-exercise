package hu.gyarmati.kemarkiexercise.controller;

import hu.gyarmati.kemarkiexercise.dto.*;
import hu.gyarmati.kemarkiexercise.service.ContactInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/contact-informations")
@Slf4j
public class ContactInformationController {
    private final ContactInformationService contactInformationService;

    @Autowired
    public ContactInformationController(ContactInformationService contactInformationService) {
        this.contactInformationService = contactInformationService;
    }

    @PostMapping
    public ResponseEntity<ContactInformationInfoDto> saveContactInformation(@RequestBody SaveAndUpdateContactInformationDto saveAndUpdateContactInformationDto) {
        log.info("Http request POST /api/contact-informations with body: " + saveAndUpdateContactInformationDto.toString());
        ContactInformationInfoDto contactInformationInfoDto = contactInformationService.saveAddress(saveAndUpdateContactInformationDto);
        return new ResponseEntity<>(contactInformationInfoDto, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInformationInfoDto> getContactInformationById(@PathVariable Long id) {
        log.info("Http request GET /api/contact-informations/{id} with path variable: " + id);
        ContactInformationInfoDto contactInformationInfoDto = contactInformationService.getContactInformationById(id);
        return new ResponseEntity<>(contactInformationInfoDto, HttpStatus.OK);
    }
}
