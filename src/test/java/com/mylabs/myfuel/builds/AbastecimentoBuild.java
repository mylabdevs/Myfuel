package com.mylabs.myfuel.builds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoInput;
import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoModel;
import com.mylabs.myfuel.domain.dto.abastecimento.VeiculoIdInput;
import com.mylabs.myfuel.domain.entity.Abastecimento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AbastecimentoBuild {

    public static String createJSONNewAbastecimentoInput() throws Exception {
        return new ObjectMapper().writeValueAsString(createNewAbatecimentoInput());
    }

    public static AbastecimentoInput createNewAbatecimentoInput() {
        return AbastecimentoInput.builder()
                .data(LocalDate.now())
                .kmAtual(BigDecimal.valueOf(2536.63))
                .posto("Posto x")
                .precoCombustivel(BigDecimal.valueOf(3.99))
                .valor(BigDecimal.valueOf(40.0))
                .veiculo(createNewVeiculoIdInput())
                .build();
    }

    public static VeiculoIdInput createNewVeiculoIdInput() {
        return VeiculoIdInput.builder().id(1L).build();
    }

    public static Abastecimento createNewAbastecimeto() {
        return Abastecimento.builder()
                .id(1l)
                .data(LocalDate.now())
                .kmAtual(BigDecimal.valueOf(2536.63))
                .posto("Posto x")
                .precoCombustivel(BigDecimal.valueOf(3.99))
                .valor(BigDecimal.valueOf(40.0))
                .veiculo(VeiculoBuild.createNewVeiculo())
                .build();
    }
    
    public static Abastecimento createNewAbastecimentoSave() {
    	return Abastecimento.builder()
    			.data(LocalDate.now())
    			.kmAtual(BigDecimal.valueOf(2536.63))
    			.posto("Posto x")
    			.precoCombustivel(BigDecimal.valueOf(3.99))
    			.valor(BigDecimal.valueOf(40.0))
    			.veiculo(VeiculoBuild.createNewVeiculo())
    			.build();
    }

    public static AbastecimentoModel createNewAbastecimentoModel() {
        return AbastecimentoModel.builder()
                .id(1l)
                .data(LocalDate.now())
                .kmAtual(BigDecimal.valueOf(2536.63))
                .posto("Posto x")
                .precoCombustivel(BigDecimal.valueOf(3.99))
                .valor(BigDecimal.valueOf(40.0))
                .veiculo(VeiculoBuild.createNewVeiculoResumoModel())
                .build();
    }
}
