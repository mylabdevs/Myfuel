package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.entity.Usuario;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.infraestrutura.service.CrudUserService;
import com.mylabs.myfuel.builds.UserBuild;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
public class UsuarioServiceTest {

    UserService service;

    @MockBean
    UserRepository repository;

    @BeforeEach
    public void setup(){
        this.service = new CrudUserService(repository);
    }

    @Test
    @DisplayName("Deve salvar um usuario")
    @Disabled
    public void saveUsertest(){

        // Cenário
        Mockito.when(repository.save(Mockito.any(Usuario.class)))
                .thenReturn(UserBuild.createUser());

        // Execução
        Usuario usuario = service.save(UserBuild.createNewUser());

        // Verificação
        assertThat(usuario.getId()).isNotNull();
        assertThat(usuario.getEmail()).isEqualTo("user@teste.com.br");
    }

}