package com.tauber.usercalculator.controller;


import static org.springframework.http.HttpStatus.OK;

import com.tauber.usercalculator.model.dto.LoveCalculatorDTO;
import com.tauber.usercalculator.model.dto.LoveDTO;
import com.tauber.usercalculator.model.entity.Love;
import com.tauber.usercalculator.model.entity.Response;
import com.tauber.usercalculator.service.impl.LoveCalculatorServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/love/v1")
@RequiredArgsConstructor
@Slf4j
public class LoveCalculatorController {

    private final LoveCalculatorServiceImpl loveCalculatorService;

    @GetMapping("/calculate")
    public Mono<Response<LoveCalculatorDTO>> calculateLove(@RequestParam String fname, @RequestParam String sname) {
        log.info("Calculating love between {} and {}", fname, sname);
        return loveCalculatorService.calculateLove(fname, sname)
            .doOnNext(loveCalculatorDTO -> log.info("Love calculated: {}", loveCalculatorDTO))
            .map(loveCalculatorDTO -> Response.<LoveCalculatorDTO>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(200)
                .status(OK)
                .message("Love calculated successfully")
                .data(Map.of("Love", loveCalculatorDTO))
                .build());
    }

    @GetMapping(value = "/get-all", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Flux<Love> getAll() {
        log.info("Fetching all Loves");
        return loveCalculatorService.getAllAddresses();
    }

    @GetMapping("/calculate-users-love/{id1}/{id2}")
    public Mono<Response<Love>> calculateUsersLove(@PathVariable Long id1, @PathVariable Long id2) {
        log.info("Calculating love between users with id {} and {}", id1, id2);
        return loveCalculatorService.calculateLoveBetweenUsers(List.of(id1, id2))
            .doOnNext(love -> log.info("Love calculated: {}", love))
            .map(love -> Response.<Love>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(200)
                .status(OK)
                .message("Love calculated successfully")
                .data(Map.of("Love", love))
                .build());
    }

    @PutMapping( "/update-love/{id}")
    public Mono<Love> update(@PathVariable Long id, @RequestBody LoveDTO loveDTO) {
        return loveCalculatorService.updateLove(id, loveDTO);
    }

}
