package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.builds.VeiculoBuild;
import com.mylabs.myfuel.domain.dto.mapper.VeiculoMapper;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Optional;

import static com.mylabs.myfuel.builds.VeiculoBuild.createNewVeiculo;
import static com.mylabs.myfuel.builds.VeiculoBuild.createNewVeiculoModel;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = VeiculoController.class)
@AutoConfigureMockMvc
public class VeiculoControllerTest {

    static String VEICULO_URL = "/veiculos";

    VeiculoController veiculoController;

    @MockBean
    VeiculoService veiculoService;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Deve salvar veiculo")
    public void createVeiculoTest() throws Exception {

        // Cenário
        String json = VeiculoBuild.createJSONNewVeiculoInput();

        Veiculo veiculo = VeiculoBuild.createNewVeiculo();

        BDDMockito.given(veiculoService.save(Mockito.any(Veiculo.class)))
                .willReturn(veiculo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(VEICULO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(createNewVeiculoModel().getId()))
                .andExpect(jsonPath("user.id").value(createNewVeiculoModel().getUser().getId()))
                .andExpect(jsonPath("user.name").value(createNewVeiculoModel().getUser().getName()))
                .andExpect(jsonPath("modelo").value(createNewVeiculoModel().getModelo()))
                .andExpect(jsonPath("marca").value(createNewVeiculoModel().getMarca()))
                .andExpect(jsonPath("km").value(createNewVeiculoModel().getKm()))
                .andExpect(jsonPath("capacidadeTanque").value(createNewVeiculoModel().getCapacidadeTanque()))
                .andExpect(jsonPath("ano").value(createNewVeiculoModel().getAno()));

    }

    @Test
    @DisplayName("Deve buscar um veiculo por id")
    public void buscarVeiculoIdTest() throws Exception {

        // Cenário
        Long veiculoId = 1l;

        BDDMockito.given(veiculoService.findById(Mockito.anyLong()))
                .willReturn(Optional.of(createNewVeiculo()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(VEICULO_URL.concat("/" + veiculoId))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(veiculoId))
                .andExpect(jsonPath("user.id").value(createNewVeiculo().getUser().getId()))
                .andExpect(jsonPath("user.name").value(createNewVeiculo().getUser().getName()))
                .andExpect(jsonPath("modelo").value(createNewVeiculo().getModelo()))
                .andExpect(jsonPath("marca").value(createNewVeiculo().getMarca()))
                .andExpect(jsonPath("km").value(createNewVeiculo().getKm()))
                .andExpect(jsonPath("capacidadeTanque").value(createNewVeiculo().getCapacidadeTanque()))
                .andExpect(jsonPath("ano").value(createNewVeiculo().getAno()));

    }

    @Test
    @DisplayName("Deve buscar um veiculos por id usuario")
    public void buscarVeiculoUserIdTest() throws Exception {

        // Cenário
        Long userId = 1l;

        String json = VeiculoBuild.createJSONListVeiculoModel();

        BDDMockito.given(veiculoService.findByUserId(Mockito.anyLong()))
                .willReturn(Arrays.asList(createNewVeiculo()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(VEICULO_URL.concat("/user/" + userId))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    @DisplayName("Deve excluir um veiculo")
    public void deleteVeiculoTest() throws Exception {

        // Cenário
        Long veiculoId = 1l;

        BDDMockito.given(veiculoService.findById(Mockito.anyLong()))
                .willReturn(Optional.of(createNewVeiculo()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(VEICULO_URL.concat("/" + veiculoId));

        mvc
                .perform(request)
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("Deve retornar not found quando tentar excluir um veiculo inexistente")
    public void deleteInexistentVeiculoTest() throws Exception {

        // Cenário
        BDDMockito.given(veiculoService.getById(Mockito.anyLong()))
                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(VEICULO_URL.concat("/" + 1));

        mvc
                .perform(request)
                .andExpect(status().isNotFound());

    }
}