package com.tauber.usercalculator.service.impl;

import com.tauber.usercalculator.model.dto.AddressDTO;
import com.tauber.usercalculator.model.entity.UserProfile;

import com.tauber.usercalculator.repository.UserProfileRepository;
import com.tauber.usercalculator.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final WebClient viaCepWebClient;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, @Qualifier("viaCepWebClient") WebClient viaCepWebClient) {
        this.userProfileRepository = userProfileRepository;
        this.viaCepWebClient = viaCepWebClient;
    }

    @Override
    public Mono<UserProfile> register(UserProfile userProfile) {
        return this.findAddress(userProfile.getAddressDTO().getCep())
            .flatMap(addressDTO -> {
                log.info("Address found: {}", addressDTO);
                userProfile.setAddressDTO(addressDTO);
                log.info("Saving user profile: {}", userProfile);
                return userProfileRepository.save(userProfile);
            });
    }

    @Override
    public Mono<UserProfile> findUserById(Long id) {
        return userProfileRepository.findById(id);
    }

    private Mono<AddressDTO> findAddress(String cep) {
        return viaCepWebClient.get()
            .uri(uriBuilder -> uriBuilder.path("/" + cep + "/json")
                .build())
            .retrieve()
            .bodyToMono(AddressDTO.class).log();
    }
}
