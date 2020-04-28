package com.mylabs.myfuel.domain.dto.mapper;

import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.Veiculo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeiculoMapper implements EntityMapper<VeiculoInput, VeiculoModel, Veiculo> {

    private final ModelMapper modelMapper;

    @Override
    public Veiculo toEntity(VeiculoInput input) {
        return modelMapper.map(input, Veiculo.class);
    }

    @Override
    public VeiculoModel toModel(Veiculo entity) {
        return modelMapper.map(entity, VeiculoModel.class);
    }

    @Override
    public List<Veiculo> toEntity(List<VeiculoInput> inputList) {
        return inputList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<VeiculoModel> toModel(List<Veiculo> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
