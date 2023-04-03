package com.tauber.usercalculator.service;

import com.tauber.usercalculator.model.dto.UserProfileDTO;
import com.tauber.usercalculator.model.entity.UserProfile;
import reactor.core.publisher.Mono;

public interface UserProfileService {

    Mono<UserProfile> register(UserProfile userProfile);
    Mono<UserProfile> findUserById(Long id);

    Mono<UserProfile> updateUser(Long id, UserProfileDTO userProfileDTO);
}
