package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.builds.VeiculoBuild;
import com.mylabs.myfuel.domain.dto.mapper.VeiculoMapper;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.entity.Usuario;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.enuns.RoleEnum;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.domain.service.VeiculoService;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mylabs.myfuel.builds.VeiculoBuild.createNewVeiculo;
import static com.mylabs.myfuel.builds.VeiculoBuild.createNewVeiculoModel;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
//@Disabled
public class VeiculoControllerTest {

    static String VEICULO_URL = "/veiculos";

    private final String EMAIL = "teste@teste.com";
    private final String NAME = "teste";
    private final String PASSWORD = "123456";

    @Autowired
    private UserRepository userRepository;

    @MockBean
    VeiculoService veiculoService;

    @MockBean
    VeiculoMapper veiculoMapper;

    @Autowired
    MockMvc mvc;

    private Usuario usuario;

    private String token = "";

    @BeforeEach
    public void setup() throws Exception {
        BDDMockito.given(veiculoMapper.toEntity(Mockito.any(VeiculoInput.class)))
                .willReturn(VeiculoBuild.createNewVeiculo());
        BDDMockito.given(veiculoMapper.toModel(Mockito.any(Veiculo.class)))
                .willReturn(VeiculoBuild.createNewVeiculoModel());
        BDDMockito.given(veiculoMapper.toModels(Mockito.any(List.class)))
                .willReturn(Arrays.asList(VeiculoBuild.createNewVeiculoModel()));

        usuario = userRepository.save(Usuario.builder()
                .password(new BCryptPasswordEncoder().encode(PASSWORD))
                .name(NAME)
                .email(EMAIL)
                .dataCadastro(LocalDate.now())
                .role(RoleEnum.ROLE_USER)
                .build());
        token = this.obtainAccessToken();
    }

    @AfterEach
    public void end() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar veiculo")
    @WithMockUser
    public void createVeiculoTest() throws Exception {

        // Cenário
        String json = VeiculoBuild.createJSONNewVeiculoInput();

        Veiculo veiculo = VeiculoBuild.createNewVeiculo();

        BDDMockito.given(veiculoService.save(Mockito.any(Veiculo.class)))
                .willReturn(veiculo);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(VEICULO_URL)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(createNewVeiculoModel().getId()))
                .andExpect(jsonPath("usuario.id").value(createNewVeiculoModel().getUsuario().getId()))
                .andExpect(jsonPath("usuario.name").value(createNewVeiculoModel().getUsuario().getName()))
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
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(veiculoId))
                .andExpect(jsonPath("usuario.id").value(createNewVeiculo().getUsuario().getId()))
                .andExpect(jsonPath("usuario.name").value(createNewVeiculo().getUsuario().getName()))
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
                .header("Authorization", "Bearer " + token)
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
                .delete(VEICULO_URL.concat("/" + veiculoId))
                .header("Authorization", "Bearer " + token);

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
                .delete(VEICULO_URL.concat("/" + 1)).header("Authorization", "Bearer " + token);

        mvc
                .perform(request)
                .andExpect(status().isNotFound());

    }

    public String obtainAccessToken() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", EMAIL);
        params.add("password", PASSWORD);
//        params.add("scope", "openid");

//        String base64 = new String(Base64.encodeBase64("user:password".getBytes()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/oauth/token")
                .params(params)
                .header("Authorization", "Basic ZGV2RnVlbFdlYjpASjk1bjRzNzVqaDUyQQ==")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .accept("*/*");

        ResultActions result = mvc
                .perform(request)
                .andExpect(status().isOk());

        String content = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser parser = new JacksonJsonParser();

        return parser.parseMap(content).get("access_token").toString();
    }
}