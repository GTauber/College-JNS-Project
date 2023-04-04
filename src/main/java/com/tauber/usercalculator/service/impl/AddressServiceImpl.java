package com.tauber.usercalculator.service.impl;

import com.tauber.usercalculator.model.entity.Address;
import com.tauber.usercalculator.repository.AddressRepository;
import com.tauber.usercalculator.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public Flux<Address> getAllAddresses() {
        return addressRepository.findAll().log();
    }

}
