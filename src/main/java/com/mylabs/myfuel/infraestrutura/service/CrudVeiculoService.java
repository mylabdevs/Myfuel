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
import org.springframework.stereotype.Service;

@Service
public class CrudVeiculoService implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final UserRepository userRepository;

    private final VeiculoMapper veiculoMapper;

    public CrudVeiculoService(VeiculoRepository veiculoRepository, UserRepository userRepository, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.userRepository = userRepository;
        this.veiculoMapper = veiculoMapper;
    }

    @Override
    public VeiculoModel save(VeiculoInput veiculoInput) {

        User user = userRepository.findById(veiculoInput.getUser().getId())
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));

        Veiculo veiculo = veiculoMapper.inputToEntity(veiculoInput);
        veiculo.setUser(user);
        veiculo = veiculoRepository.save(veiculo);
        return veiculoMapper.entityToModel(veiculo);
    }
}
