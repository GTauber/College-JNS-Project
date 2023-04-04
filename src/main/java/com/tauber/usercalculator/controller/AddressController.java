package com.tauber.usercalculator.controller;

import com.tauber.usercalculator.model.entity.Address;
import com.tauber.usercalculator.service.impl.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/address/v1")
@Slf4j
@RequiredArgsConstructor
public class AddressController {

    private final AddressServiceImpl addressService;

    @GetMapping(value = "/get-all", produces = "application/stream+json")
    public Flux<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

}
