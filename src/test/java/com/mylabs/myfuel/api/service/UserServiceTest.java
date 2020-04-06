package com.mylabs.myfuel.api.service;

import com.mylabs.myfuel.api.entity.model.User;
import com.mylabs.myfuel.api.repository.UserRepository;
import com.mylabs.myfuel.api.service.impl.UserServiceImpl;
import com.mylabs.myfuel.api.util.ApiUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    UserService service;

    @MockBean
    UserRepository repository;

    @BeforeEach
    public void setup(){
        this.service = new UserServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um usuario")
    public void saveUsertest(){
        // Cenário
        User user = ApiUtils.createNewUser();

        Mockito.when(repository.save(Mockito.any(User.class)))
                .thenReturn(ApiUtils.createUser());

        // Execução
        User saveUser = service.save(user);

        // Verificação
        assertThat(saveUser.getId()).isNotNull();
        assertThat(saveUser.getEmail()).isEqualTo("user@teste.com.br");
    }

}