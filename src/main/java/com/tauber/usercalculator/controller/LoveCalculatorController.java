package com.tauber.usercalculator.controller;


import static org.springframework.http.HttpStatus.OK;

import com.tauber.usercalculator.model.dto.LoveCalculatorDTO;
import com.tauber.usercalculator.model.entity.Response;
import com.tauber.usercalculator.service.impl.LoveCalculatorServiceImpl;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/love-calculator")
@RequiredArgsConstructor
@Slf4j
public class LoveCalculatorController {

    private final LoveCalculatorServiceImpl loveCalculatorServiceImpl;

    @GetMapping("/calculate")
    public Mono<Response<LoveCalculatorDTO>> calculateLove(@RequestParam String fname, @RequestParam String sname) {
        log.info("Calculating love between {} and {}", fname, sname);
        return loveCalculatorServiceImpl.calculateLove(fname, sname)
            .doOnNext(loveCalculatorDTO -> log.info("Love calculated: {}", loveCalculatorDTO))
            .map(loveCalculatorDTO -> Response.<LoveCalculatorDTO>builder()
                .timestamp(LocalDateTime.now())
                .statusCode(200)
                .status(OK)
                .message("Love calculated successfully")
                .data(Map.of("Love", loveCalculatorDTO))
                .build());
    }
}
