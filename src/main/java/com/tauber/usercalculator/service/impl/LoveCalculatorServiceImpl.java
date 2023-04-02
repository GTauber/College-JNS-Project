package com.tauber.usercalculator.service.impl;

import com.tauber.usercalculator.model.dto.LoveCalculatorDTO;
import com.tauber.usercalculator.service.LoveCalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LoveCalculatorServiceImpl implements LoveCalculatorService {

    private final WebClient webClient;

    public LoveCalculatorServiceImpl(@Qualifier("amorWebClient") WebClient webClient) {
        this.webClient = webClient;
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
}
