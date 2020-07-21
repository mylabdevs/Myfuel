package com.mylabs.myfuel.domain.dto.abastecimento;

import com.mylabs.myfuel.domain.dto.veiculo.UserResumoModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResumoModel {

    private Long id;

    private UserResumoModel usuario;

    private String modelo;

    private String marca;

    private String placa;

    private String cor;
}
