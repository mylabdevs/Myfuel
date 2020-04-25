package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.service.UserService;
import com.mylabs.myfuel.builds.UserBuild;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    static String USER_URL = "/user";

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService service;

    @Test
    @DisplayName("Deve salvar usuario")
    public void createNewUsuario() throws Exception {

        // Cenário
        User user = UserBuild.createNewUser();

        String json = UserBuild.userDTOToJson();

        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(user);

        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Validações
        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("name").value(user.getName()))
                .andExpect(jsonPath("email").value(user.getEmail()))
                .andExpect(jsonPath("dataCadastro").value(getDateNow()));
    }


    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficiente para criar usuario")
    public void createUserInvalidTest() throws Exception {
        String json = UserBuild.userDTOInvalidJson();

        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Validações
        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("campos", hasSize(3)))
                .andExpect(jsonPath("titulo").value("Um ou mais campos inválidos"));

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não informar email invalido")
    public void createUserEmailInvalidTest() throws Exception {
        String json = UserBuild.userDTOEmailInvalidJson();

        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Validações
        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("campos[0].nome").value("email"))
                .andExpect(jsonPath("campos[0].mensagem").value("deve ser um e-mail válido"));

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não informar senha invalido")
    public void createUserPassworInvalidTest() throws Exception {
        String json = UserBuild.userDTOPasswordlInvalidJson();

        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Validações
        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("campos[0].nome").value("password"))
                .andExpect(jsonPath("campos[0].mensagem").value("deve ter no mínimo 6 e no máximo 12 caracteres"));

    }

    private String getDateNow() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
