package com.tauber.usercalculator.repository;

import com.tauber.usercalculator.model.entity.UserProfile;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserProfileRepository extends ReactiveCrudRepository<UserProfile, Long> { //TODO Just changed this Long, it was String -- testing purposes

    @Query("""
    INSERT INTO user_profile (version, name, email, tel, cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi)
    VALUES (0, :#{#userProfile.name}, :#{#userProfile.email}, :#{#userProfile.tel},
            :#{#userProfile.addressDTO.cep}, :#{#userProfile.addressDTO.logradouro}, :#{#userProfile.addressDTO.complemento},\s
            :#{#userProfile.addressDTO.bairro}, :#{#userProfile.addressDTO.localidade}, :#{#userProfile.addressDTO.uf},\s
            :#{#userProfile.addressDTO.ibge}, :#{#userProfile.addressDTO.gia}, :#{#userProfile.addressDTO.ddd}, :#{#userProfile.addressDTO.siafi})
            """)
    Mono<Void> saveWithAddress(@Param("userProfile") UserProfile userProfile);

}
