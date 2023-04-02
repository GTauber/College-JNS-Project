package com.tauber.usercalculator.model.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Love {

    @Id
    private Long id;

    @Version
    private Integer version;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Long idFUser;

    private Long idSUser;

    private Long percentage;

    private String result;

}
