package hu.gyarmati.kemarkiexercise.controller;

import hu.gyarmati.kemarkiexercise.dto.*;
import hu.gyarmati.kemarkiexercise.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/addresses")
@Slf4j
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressInfoDto> saveAddress(@RequestBody SaveAndUpdateAddressDto saveAndUpdateAddressDto) {
        log.info("Http request POST /api/address with body: " + saveAndUpdateAddressDto.toString());
        AddressInfoDto addressInfoDto = addressService.saveAddress(saveAndUpdateAddressDto);
        return new ResponseEntity<>(addressInfoDto, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDetailsDto> getAddressById(@PathVariable Long id) {
        log.info("Http request GET /api/addresses/{id} with path variable: " + id);
        AddressDetailsDto addressDetailsDto = addressService.getAddressById(id);
        return new ResponseEntity<>(addressDetailsDto, HttpStatus.OK);
    }
}
