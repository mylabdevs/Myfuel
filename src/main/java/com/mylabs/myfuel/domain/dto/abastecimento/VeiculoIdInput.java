package com.mylabs.myfuel.domain.dto.abastecimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoIdInput {

    @NotNull
    private Long id;
}
