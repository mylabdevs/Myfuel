package com.mylabs.myfuel.domain.dto.veiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoModel {

    private Long id;

    private UserResumoModel user;

    private String modelo;

    private String marca;

    private Double km;

    private Double capacidadeTanque;

    private Integer ano;
}
