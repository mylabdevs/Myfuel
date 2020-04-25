package com.mylabs.myfuel.infraestrutura.service;

import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.exception.NegocioException;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.domain.repository.VeiculoRepository;
import com.mylabs.myfuel.domain.service.VeiculoService;
import org.springframework.stereotype.Service;

@Service
public class CrudVeiculoServcice implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final UserRepository userRepository;

    public CrudVeiculoServcice(VeiculoRepository veiculoRepository, UserRepository userRepository) {
        this.veiculoRepository = veiculoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Veiculo save(Veiculo veiculo) {
        User user = userRepository.findById(veiculo.getUser().getId())
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));
        veiculo.setUser(user);
        Veiculo save = veiculoRepository.save(veiculo);
        return save;
    }
}
