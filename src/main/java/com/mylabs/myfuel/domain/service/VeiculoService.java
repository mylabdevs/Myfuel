package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.entity.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoService {

    Veiculo save(Veiculo veiculo);

    Veiculo getById(Long veiculoId);

    void delete(Veiculo veiculo);

    Optional<Veiculo> findById(Long veiculoId);

    List<Veiculo> findByUserId(Long userId);
}
