package com.tauber.usercalculator.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fname",
    "sname",
    "percentage",
    "result"
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoveCalculatorDTO {

    @JsonProperty("fname")
    public String fname;
    @JsonProperty("sname")
    public String sname;
    @JsonProperty("percentage")
    public Long percentage;
    @JsonProperty("result")
    public String result;

}