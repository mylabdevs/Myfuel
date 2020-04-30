package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.builds.UserBuild;
import com.mylabs.myfuel.builds.VeiculoBuild;
import com.mylabs.myfuel.domain.dto.mapper.VeiculoMapper;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.domain.repository.VeiculoRepository;
import com.mylabs.myfuel.infraestrutura.service.CrudVeiculoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mylabs.myfuel.builds.VeiculoBuild.createNewVeiculo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class VeiculoServiceTest {

    VeiculoService veiculoService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    VeiculoRepository veiculoRepository;

    @BeforeEach
    public void setup() {
        this.veiculoService = new CrudVeiculoService(veiculoRepository, userRepository);
    }

    @Test
    public void createVeiculoTest() {

        // Cenário
        Veiculo veiculo = createNewVeiculo();

        BDDMockito.given(userRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(UserBuild.createNewUser()));

        BDDMockito.given(veiculoRepository.save(Mockito.any(Veiculo.class)))
                .willReturn(veiculo);

        // Execução
        Veiculo veiculoResponse = veiculoService.save(veiculo);

        // Verificação

        assertThat(veiculoResponse).isNotNull();
        assertThat(veiculoResponse.getId()).isEqualTo(veiculo.getId());
        assertThat(veiculoResponse.getUser().getId()).isEqualTo(veiculo.getUser().getId());
        assertThat(veiculoResponse.getModelo()).isEqualTo(veiculo.getModelo());
        assertThat(veiculoResponse.getMarca()).isEqualTo(veiculo.getMarca());
        assertThat(veiculoResponse.getKm()).isEqualTo(veiculo.getKm());
        assertThat(veiculoResponse.getPlaca()).isEqualTo(veiculo.getPlaca());
        assertThat(veiculoResponse.getCor()).isEqualTo(veiculo.getCor());

    }

    @Test
    @DisplayName("Deve excluir um usuario")
    public void deleteVeiculotest() {

        // Cenário
        Veiculo veiculo = createNewVeiculo();

        // Execuçãp

        Assertions.assertDoesNotThrow(() -> veiculoService.delete(veiculo));

        // Verificações
        Mockito.verify(veiculoRepository, Mockito.times(1)).delete(veiculo);

    }

    @Test
    @DisplayName("Deve ocorrer um erro ao tentar deletar um veiculo inexistente")
    public void deleteInvalidBookTest() {

        // Cenário
        Veiculo veiculo = new Veiculo();

        // Execução
        assertThrows(IllegalArgumentException.class, () -> veiculoService.delete(veiculo));

        // Verificações
        Mockito.verify(veiculoRepository, Mockito.never()).delete(veiculo);
    }

    @Test
    @DisplayName("Deve buscar veiculo por id")
    public void findByIdVeiculoTest() {

        // Cenário
        Long id = 1l;
        Veiculo veiculo = createNewVeiculo();
        veiculo.setId(id);

        Mockito.when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculo));

        // Execução
        Veiculo foundVeiculo = veiculoService.getById(id);

        // Verificações
        assertThat(foundVeiculo.getId()).isEqualTo(id);
        assertThat(foundVeiculo.getMarca()).isEqualTo(veiculo.getMarca());
        assertThat(foundVeiculo.getModelo()).isEqualTo(veiculo.getModelo());
        assertThat(foundVeiculo.getKm()).isEqualTo(veiculo.getKm());

    }

    @Test
    @DisplayName("Deve buscar Lista de veiculo por id do usuario")
    public void findByUserIdVeiculoTest() {

        // Cenário
        Long userId = 1l;


        Mockito.when(veiculoRepository.findByUserId(userId)).thenReturn(Arrays.asList(createNewVeiculo()));

        // Execução
        List<Veiculo> foundVeiculos = veiculoService.findByUserId(userId);

        // Verificações
        assertThat(foundVeiculos.isEmpty()).isFalse();
        assertThat(foundVeiculos.size()).isEqualTo(1);
        assertThat(foundVeiculos.get(0).getModelo()).isEqualTo(createNewVeiculo().getModelo());
        assertThat(foundVeiculos.get(0).getMarca()).isEqualTo(createNewVeiculo().getMarca());

    }
}