package com.tauber.usercalculator.repository;

import com.tauber.usercalculator.model.entity.UserProfile;
import java.util.List;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserProfileRepository extends ReactiveCrudRepository<UserProfile, Long> { //TODO Just changed this Long, it was String -- testing purposes

    @Query("SELECT * FROM user_profile WHERE id IN (:ids)")
    Flux<UserProfile> findUsersById(@Param("ids") List<Long> ids);

    @Modifying
    @Query("DELETE FROM user_profile WHERE id = :id")
    Mono<Integer> deleteUserById(@Param("id") Long id);
}
