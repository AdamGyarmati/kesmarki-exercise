package hu.gyarmati.kemarkiexercise.service;

import hu.gyarmati.kemarkiexercise.dto.AddressInfoDto;
import hu.gyarmati.kemarkiexercise.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImp implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImp(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressInfoDto saveAddress() {
        return null;
    }
}
