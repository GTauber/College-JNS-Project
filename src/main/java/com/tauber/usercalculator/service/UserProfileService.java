package com.tauber.usercalculator.service;

import com.tauber.usercalculator.model.dto.UserProfileDTO;
import com.tauber.usercalculator.model.entity.UserProfile;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserProfileService {

    Mono<UserProfile> register(UserProfile userProfile);
    Mono<UserProfile> findUserById(Long id);

    Mono<UserProfile> updateUser(Long id, UserProfileDTO userProfileDTO);

    Mono<Boolean> deleteUser(Long id);

    Flux<UserProfile> findUsersByIds(List<Long> userIds);

}
