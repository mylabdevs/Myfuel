package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.builds.VeiculoBuild;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.service.VeiculoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mylabs.myfuel.builds.VeiculoBuild.createNewVeiculoModel;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = VeiculoController.class)
@AutoConfigureMockMvc
public class VeiculoControllerTest {

    static String VEICULO_URL = "/veiculos";

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
}