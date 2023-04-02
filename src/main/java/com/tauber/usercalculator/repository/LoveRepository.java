package com.tauber.usercalculator.repository;

import com.tauber.usercalculator.model.entity.Love;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoveRepository extends ReactiveCrudRepository<Love, Long> {

}
