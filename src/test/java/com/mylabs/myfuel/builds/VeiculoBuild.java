package com.mylabs.myfuel.builds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylabs.myfuel.domain.dto.veiculo.UserIdInput;
import com.mylabs.myfuel.domain.dto.veiculo.UserResumoModel;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.entity.Veiculo;

public class VeiculoBuild {

    public static Veiculo getVeiculoSave() {
        return Veiculo.builder()
                .id(1l)
                .user(User.builder().id(1l).build())
                .marca("Spacefox")
                .modelo("VW")
                .km(1000.0)
                .ano(2008)
                .capacidadeTanque(250.0)
                .build();
    }

    public static String userDTOToJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(createNewVeiculoInput());
    }

    public static VeiculoInput createNewVeiculoInput() {
        return VeiculoInput.builder()
                .user(getUserInput())
                .marca("Spacefox")
                .modelo("VW")
                .km(1000.0)
                .ano(2008)
                .capacidadeTanque(250.0)
                .build();
    }

    public static VeiculoModel createNewVeiculoModel() {
        return VeiculoModel.builder()
                .id(1L)
                .user(getUserResumoModel())
                .ano(2008)
                .capacidadeTanque(250.0)
                .marca("VW")
                .modelo("spacefox")
                .km(125.0)
                .build();
    }

    public static Veiculo createNewVeiculo() {
        return Veiculo.builder()
                .id(1L)
                .user(UserBuild.createUser())
                .ano(2008)
                .capacidadeTanque(250.0)
                .marca("VW")
                .modelo("spacefox")
                .km(125.0)
                .build();
    }

    public static String createJSONNewVeiculoInput() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(createNewVeiculoInput());
    }

    public static UserResumoModel getUserResumoModel() {
        return UserResumoModel.builder().id(1L).name("userTeste").build();
    }

    public static UserIdInput getUserInput() {
        return UserIdInput.builder().id(1l).build();
    }

}
