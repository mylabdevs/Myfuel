package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.api.entity.dto.UserDTO;
import com.mylabs.myfuel.api.entity.model.User;
import com.mylabs.myfuel.api.service.UserService;
import com.mylabs.myfuel.api.util.ApiUtils;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    static String USER_URL = "/api/user";

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService service;

    @Test
    @DisplayName("Deve salvar usuario")
    public void createNewUsuario() throws Exception {

        // Cenário
        UserDTO userDTO = ApiUtils.createNewUserDTO();

        User userSave = ApiUtils.createNewUser();

        String json = ApiUtils.userDTOToJson();

        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(userSave);

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
                .andExpect(jsonPath("id").value(userSave.getId()))
                .andExpect(jsonPath("name").value(userSave.getName()))
                .andExpect(jsonPath("email").value(userSave.getEmail()));
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficiente para criar usuario")
    public void createUserInvalidTest() throws Exception {
        String json = ApiUtils.userDTOInvalidJson();

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
                .andExpect(jsonPath("erros", hasSize(3)));

        }

    @Test
    @DisplayName("Deve lançar erro de validação quando não informar email invalido")
    public void createUserEmailInvalidTest() throws Exception {
        String json = ApiUtils.userDTOEmailInvalidJson();

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
                .andExpect(jsonPath("erros[0]").value("Informe um email válido"));

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não informar senha invalido")
    public void createUserPassworInvalidTest() throws Exception {
        String json = ApiUtils.userDTOPasswordlInvalidJson();

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
                .andExpect(jsonPath("erros[0]").value("A senha deve ter entre 6 e no máximo 12 caracteres"));

    }
}
