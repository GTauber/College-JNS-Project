package com.tauber.usercalculator.controller;


import static org.springframework.http.HttpStatus.CREATED;

import com.tauber.usercalculator.model.dto.UserProfileDTO;
import com.tauber.usercalculator.model.entity.Response;
import com.tauber.usercalculator.model.entity.UserProfile;
import com.tauber.usercalculator.service.impl.UserProfileServiceImpl;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/user/v1")
public class UserProfileController {

    private final UserProfileServiceImpl userProfileService;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public Mono<Response<UserProfile>> register(@RequestBody UserProfileDTO userProfileDTO) {
        log.info("Registering user {}", userProfileDTO);
        return userProfileService.register(modelMapper.map(userProfileDTO, UserProfile.class))
            .map(user -> Response.<UserProfile>builder()
                .timestamp(LocalDateTime.now())
                .data(Map.of("user", user))
                .message("User registered successfully")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());
    }

    @GetMapping("/getId/{id}")
    public Mono<Response<UserProfile>> getUserById(@PathVariable Long id) {
        log.info("Getting user by id {}", id);
        return userProfileService.findUserById(id)
            .map(user -> Response.<UserProfile>builder()
                .timestamp(LocalDateTime.now())
                .data(Map.of("user", user))
                .message("User retrieved successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @PutMapping("/update/{id}")
    public Mono<Response<UserProfile>> updateUser(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO) {
        log.info("Updating user {}", userProfileDTO);
        return userProfileService.updateUser(id, userProfileDTO)
            .map(user -> Response.<UserProfile>builder()
                .timestamp(LocalDateTime.now())
                .data(Map.of("user", user))
                .message("User updated successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Response<Boolean>> deleteUser(@PathVariable Long id) {
        return userProfileService.deleteUser(id)
            .map(deleted -> Response.<Boolean>builder()
                .timestamp(LocalDateTime.now())
                .data(Map.of("Deleted:", deleted))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

}
