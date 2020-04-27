package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.dto.mapper.UserMapper;
import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.infraestrutura.service.CrudUserService;
import com.mylabs.myfuel.builds.UserBuild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    UserService service;

    @MockBean
    UserMapper userMapper;

    @MockBean
    UserRepository repository;

    @BeforeEach
    public void setup(){
        this.service = new CrudUserService(repository, userMapper);
    }

    @Test
    @DisplayName("Deve salvar um usuario")
    public void saveUsertest(){

        // Cenário
        Mockito.when(repository.save(Mockito.any(User.class)))
                .thenReturn(UserBuild.createUser());

        Mockito.when(userMapper.inputToEntity(Mockito.any(UserInput.class)))
                .thenReturn(UserBuild.createNewUser());

        Mockito.when(userMapper.entityToModel(Mockito.any(User.class)))
                .thenReturn(UserBuild.createNewUserModel());

        // Execução
        UserModel saveUser = service.save(UserBuild.createNewUserInput());

        // Verificação
        assertThat(saveUser.getId()).isNotNull();
        assertThat(saveUser.getEmail()).isEqualTo("user@teste.com.br");
    }

}