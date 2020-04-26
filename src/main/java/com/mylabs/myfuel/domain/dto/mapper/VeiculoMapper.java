package com.mylabs.myfuel.domain.dto.mapper;

import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.Veiculo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoMapper implements EntityMapper<Veiculo, VeiculoInput, VeiculoModel> {

    private final ModelMapper modelMapper;

    @Override
    public Veiculo inputToEntity(VeiculoInput input) {
        return modelMapper.map(input, Veiculo.class);
    }

    @Override
    public VeiculoModel entityToModel(Veiculo entity) {
        return modelMapper.map(entity, VeiculoModel.class);
    }
}
