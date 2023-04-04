package com.tauber.usercalculator.service.impl;

import com.tauber.usercalculator.model.dto.UserProfileDTO;
import com.tauber.usercalculator.model.entity.Address;
import com.tauber.usercalculator.model.entity.UserProfile;
import com.tauber.usercalculator.repository.AddressRepository;
import com.tauber.usercalculator.repository.UserProfileRepository;
import com.tauber.usercalculator.service.UserProfileService;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final AddressRepository addressRepository;

    private final UserProfileRepository userProfileRepository;
    private final WebClient viaCepWebClient;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, @Qualifier("viaCepWebClient") WebClient viaCepWebClient,
        AddressRepository addressRepository) {
        this.userProfileRepository = userProfileRepository;
        this.viaCepWebClient = viaCepWebClient;
        this.addressRepository = addressRepository;
    }

    //TODO When save address, see if it already exists. If it does, just save the user with the address id
    @Override
    public Mono<UserProfile> register(UserProfile userProfile) {
        return this.findAddress(userProfile.getAddress().getCep())
            .doOnNext(address -> log.info("Address found: {}", address))
            .flatMap(addressRepository::save)
            .flatMap(address -> {
                userProfile.setAddressId(address.getId());
                userProfile.setAddress(address);
                return userProfileRepository.save(userProfile);
            });
    }

    @Override
    public Mono<UserProfile> findUserById(Long id) {
        return userProfileRepository.findById(id);
    }

    @Override
    public Mono<UserProfile> updateUser(Long id, UserProfileDTO userProfileDTO) {
        return userProfileRepository.findById(id)
            .flatMap(userProfile -> {
                userProfile.setName(Objects.requireNonNullElse(userProfileDTO.getName(), userProfile.getName()));
                userProfile.setEmail(Objects.requireNonNullElse((userProfileDTO.getEmail()), userProfile.getEmail()));
                userProfile.setTel(Objects.requireNonNullElse(userProfileDTO.getTel(), userProfile.getTel()));
                return userProfileRepository.save(userProfile);
            });
    }

    @Override
    public Mono<Boolean> deleteUser(Long id) {
        log.info("Deleting user by id {}", id);
        return userProfileRepository.deleteUserById(id)
            .map(deleted -> deleted == 1)
            .doOnNext(deleted -> log.info("User deleted: {}", deleted));
    }

    @Override
    public Flux<UserProfile> findUsersByIds(List<Long> userIds) {
        return userProfileRepository.findUsersById(userIds);
    }

    //TODO Move to address service
    private Mono<Address> findAddress(String cep) {
        return viaCepWebClient.get()
            .uri(uriBuilder -> uriBuilder.path("/" + cep + "/json")
                .build())
            .retrieve()
            .bodyToMono(Address.class);
    }
}
