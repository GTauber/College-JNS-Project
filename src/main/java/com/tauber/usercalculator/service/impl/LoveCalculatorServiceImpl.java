package com.tauber.usercalculator.service.impl;

import com.tauber.usercalculator.model.dto.LoveCalculatorDTO;
import com.tauber.usercalculator.model.dto.LoveDTO;
import com.tauber.usercalculator.model.entity.Love;
import com.tauber.usercalculator.repository.LoveRepository;
import com.tauber.usercalculator.service.LoveCalculatorService;
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
public class LoveCalculatorServiceImpl implements LoveCalculatorService {

    private final WebClient webClient;

    private final UserProfileService userProfileService;

    private final LoveRepository loveRepository;

    public LoveCalculatorServiceImpl(@Qualifier("amorWebClient") WebClient webClient, UserProfileService userProfileService,
        LoveRepository loveRepository) {
        this.webClient = webClient;
        this.userProfileService = userProfileService;
        this.loveRepository = loveRepository;
    }

    @Override
    public Mono<LoveCalculatorDTO> calculateLove(String sname, String fname) {

        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/getPercentage")
                .queryParam("fname", fname)
                .queryParam("sname", sname)
                .build())
            .retrieve()
            .bodyToMono(LoveCalculatorDTO.class).log();
    }

    @Override
    public Mono<Love> calculateLoveBetweenUsers(List<Long> userIds) {
        return userProfileService.findUsersByIds(userIds).collectList()
            .flatMap(userProfiles -> this.calculateLove(userProfiles.get(0).getName(), userProfiles.get(1).getName())
                .map(loveCalculatorDTO -> new Love(userProfiles.get(0), userProfiles.get(1), loveCalculatorDTO)));
    }

    @Override
    public Flux<Love> getAllAddresses() {
        return loveRepository.findAll();
    }

    @Override
    public Mono<Love> updateLove(Long id, LoveDTO loveDTO) {
        return loveRepository.findById(id)
            .flatMap(love -> {
                love.setIdFUser(Objects.requireNonNullElse(loveDTO.getIdFUser(), love.getIdFUser()));
                love.setIdSUser(Objects.requireNonNullElse(loveDTO.getIdSUser(), love.getIdSUser()));
                love.setPercentage(Objects.requireNonNullElse(loveDTO.getPercentage(), love.getPercentage()));
                return loveRepository.save(love);
            });
    }


}
