package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.Veiculo;

public interface VeiculoService {

    VeiculoModel save(VeiculoInput veiculoInput);
}
