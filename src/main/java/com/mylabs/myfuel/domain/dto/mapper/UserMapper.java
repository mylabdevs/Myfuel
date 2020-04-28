package com.mylabs.myfuel.domain.dto.mapper;

import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMapper implements EntityMapper<UserInput, UserModel, User> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserInput input) {
        return modelMapper.map(input, User.class);
    }

    @Override
    public UserModel toModel(User entity) {
        return modelMapper.map(entity, UserModel.class);
    }

    @Override
    public List<User> toEntity(List<UserInput> inputList) {
        return inputList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserModel> toModel(List<User> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
