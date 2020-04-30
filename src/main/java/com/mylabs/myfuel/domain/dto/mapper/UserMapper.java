package com.mylabs.myfuel.domain.dto.mapper;

import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
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
    public List<User> toEntits(List<UserInput> inputList) {
        return inputList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserModel> toModels(List<User> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
