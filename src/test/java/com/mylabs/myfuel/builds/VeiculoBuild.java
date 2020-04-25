package com.mylabs.myfuel.builds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static Object createNewVeiculoInput() {
        return Veiculo.builder()
                .user(User.builder().id(1l).build())
                .marca("Spacefox")
                .modelo("VW")
                .km(1000.0)
                .ano(2008)
                .capacidadeTanque(250.0)
                .build();
    }
}
