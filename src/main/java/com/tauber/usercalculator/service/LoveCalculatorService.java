package com.tauber.usercalculator.service;

import com.tauber.usercalculator.model.dto.LoveCalculatorDTO;
import com.tauber.usercalculator.model.dto.LoveDTO;
import com.tauber.usercalculator.model.entity.Love;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoveCalculatorService {

    Mono<LoveCalculatorDTO> calculateLove(String fname, String sname);

    Mono<Love> calculateLoveBetweenUsers(List<Long> userIds);

    Flux<Love> getAllAddresses();

    Mono<Love> updateLove(Long id, LoveDTO loveDTO);
}
