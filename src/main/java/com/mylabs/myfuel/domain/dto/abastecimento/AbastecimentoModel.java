package com.mylabs.myfuel.domain.dto.abastecimento;

import com.mylabs.myfuel.domain.entity.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbastecimentoModel {

    private Long id;

    private String posto;

    private LocalDate data;

    private BigDecimal valor;

    private BigDecimal precoCombustivel;

    private BigDecimal kmAtual;

    private VeiculoResumoModel veiculo;
}
