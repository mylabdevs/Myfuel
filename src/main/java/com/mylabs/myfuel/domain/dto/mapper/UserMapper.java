package com.mylabs.myfuel.domain.dto.mapper;

import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserMapper implements EntityMapper<UserInput, UserModel, Usuario> {

    private final ModelMapper modelMapper;

    @Override
    public Usuario toEntity(UserInput input) {
        return modelMapper.map(input, Usuario.class);
    }

    @Override
    public UserModel toModel(Usuario entity) {
        return modelMapper.map(entity, UserModel.class);
    }

    @Override
    public List<Usuario> toEntits(List<UserInput> inputList) {
        return inputList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserModel> toModels(List<Usuario> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
