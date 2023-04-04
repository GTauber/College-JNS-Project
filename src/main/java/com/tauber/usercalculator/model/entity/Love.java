package com.tauber.usercalculator.model.entity;

import com.tauber.usercalculator.model.dto.LoveCalculatorDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("Love_tb")
public class Love {

    @Id
    private Long id;

    @Version
    private Integer version;

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column("idfUser")
    private Long idFUser;

    @Column("idSUser")
    private Long idSUser;

    private Long percentage;

    private String result;

    public Love(UserProfile firstUser, UserProfile secondUser, LoveCalculatorDTO loveCalculatorDTO) {
        this.idFUser = firstUser.getId();
        this.idSUser = secondUser.getId();
        this.percentage = loveCalculatorDTO.getPercentage();
        this.result = loveCalculatorDTO.getResult();
    }

}
