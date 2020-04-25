package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.infraestrutura.service.CrudUserService;
import com.mylabs.myfuel.builds.UserBuild;
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
        this.service = new CrudUserService(repository);
    }

    @Test
    @DisplayName("Deve salvar um usuario")
    public void saveUsertest(){
        // Cenário
        User user = UserBuild.createNewUser();

        Mockito.when(repository.save(Mockito.any(User.class)))
                .thenReturn(UserBuild.createUser());

        // Execução
        User saveUser = service.save(user);

        // Verificação
        assertThat(saveUser.getId()).isNotNull();
        assertThat(saveUser.getEmail()).isEqualTo("user@teste.com.br");
    }

}