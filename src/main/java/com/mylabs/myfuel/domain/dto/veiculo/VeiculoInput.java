package com.mylabs.myfuel.domain.dto.veiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoInput {

    @Valid
    @NotNull
    private UserIdInput user;

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    private Double km;

    private Double capacidadeTanque;

    private Integer ano;

    @NotBlank
    private String placa;

    private String cor;
}
