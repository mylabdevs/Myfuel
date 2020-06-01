package com.mylabs.myfuel.domain.dto.abastecimento;

import com.mylabs.myfuel.domain.entity.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbastecimentoInput {

    @NotBlank
    private String posto;

    @NotNull
    private LocalDate data;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private BigDecimal precoCombustivel;

    @NotNull
    private BigDecimal kmAtual;

    @NotNull
    private String tipoCombustivel;

    @Valid
    @NotNull
    private VeiculoIdInput veiculo;
}
