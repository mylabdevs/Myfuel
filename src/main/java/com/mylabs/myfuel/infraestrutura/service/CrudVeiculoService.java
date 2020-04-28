package com.mylabs.myfuel.infraestrutura.service;

import com.mylabs.myfuel.domain.dto.mapper.VeiculoMapper;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.exception.NegocioException;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.domain.repository.VeiculoRepository;
import com.mylabs.myfuel.domain.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrudVeiculoService implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final UserRepository userRepository;

    @Override
    public Veiculo save(Veiculo veiculo) {

        User user = userRepository.findById(veiculo.getUser().getId())
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));

        veiculo.setUser(user);

        return veiculoRepository.save(veiculo);
    }

    @Override
    public Veiculo getById(Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Veiculo veiculo) {
        if (veiculo == null || veiculo.getId() == null) {
            throw new IllegalArgumentException("Veiculo id cante be null.");
        }
        veiculoRepository.delete(veiculo);
    }

    @Override
    public Optional<Veiculo> findById(Long veiculoId) {
        return veiculoRepository.findById(veiculoId);
    }

    @Override
    public List<Veiculo> findByUserId(Long userId) {
        return veiculoRepository.findByUserId(userId);
    }
}
