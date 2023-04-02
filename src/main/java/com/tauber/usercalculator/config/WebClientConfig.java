package com.tauber.usercalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("amorWebClient")
    public WebClient createWebClient() {
        return WebClient.builder()
            .baseUrl("https://love-calculator.p.rapidapi.com")
            .defaultHeader("X-RapidAPI-Host", "love-calculator.p.rapidapi.com")
            .defaultHeader("X-RapidAPI-Key", "51fe449fa1msh863755c0321108bp1c3adejsnc1f465b1bf3b")
            .build();
    }

    @Bean("viaCepWebClient")
    public WebClient createViaCepWebClient() {
        return WebClient.builder()
            .baseUrl("viacep.com.br/ws")
            .build();
    }
}
