package com.tauber.usercalculator.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cep",
    "logradouro",
    "complemento",
    "bairro",
    "localidade",
    "uf",
    "ibge",
    "gia",
    "ddd",
    "siafi"
})
public class AddressDTO {

    @JsonProperty("cep")
    public String cep;
    @JsonProperty("logradouro")
    public String logradouro;
    @JsonProperty("complemento")
    public String complemento;
    @JsonProperty("bairro")
    public String bairro;
    @JsonProperty("localidade")
    public String localidade;
    @JsonProperty("uf")
    public String uf;
    @JsonProperty("ibge")
    public String ibge;
    @JsonProperty("gia")
    public String gia;
    @JsonProperty("ddd")
    public String ddd;
    @JsonProperty("siafi")
    public String siafi;


}

