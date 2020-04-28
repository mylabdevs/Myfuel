package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.Veiculo;

import java.util.Optional;

public interface VeiculoService {

    Veiculo save(Veiculo veiculo);

    Veiculo getById(Long veiculoId);

    void delete(Veiculo veiculo);

    Optional<Veiculo> findById(Long veiculoId);
}
