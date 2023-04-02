package com.tauber.usercalculator.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserProfileCustomRepository {

    private final UserProfileRepository userProfileRepository;
    private final AddressRepository addressRepository;

}
