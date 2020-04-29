package com.mylabs.myfuel.domain.dto.mapper;

import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoInput;
import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoModel;
import com.mylabs.myfuel.domain.entity.Abastecimento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbastecimentoMapper implements EntityMapper<AbastecimentoInput, AbastecimentoModel, Abastecimento> {

    private final ModelMapper modelMapper;

    @Override
    public Abastecimento toEntity(AbastecimentoInput input) {
        return modelMapper.map(input, Abastecimento.class);
    }

    @Override
    public AbastecimentoModel toModel(Abastecimento entity) {
        return modelMapper.map(entity, AbastecimentoModel.class);
    }

    @Override
    public List<Abastecimento> toEntits(List<AbastecimentoInput> inputList) {
        return inputList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbastecimentoModel> toModels(List<Abastecimento> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
