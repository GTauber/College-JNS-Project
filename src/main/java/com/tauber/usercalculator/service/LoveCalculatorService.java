package com.tauber.usercalculator.service;

import com.tauber.usercalculator.model.dto.LoveCalculatorDTO;
import reactor.core.publisher.Mono;

public interface LoveCalculatorService {

    Mono<LoveCalculatorDTO> calculateLove(String fname, String sname);

}
