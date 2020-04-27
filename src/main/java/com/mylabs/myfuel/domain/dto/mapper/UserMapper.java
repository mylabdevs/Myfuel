package com.mylabs.myfuel.domain.dto.mapper;

import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper implements EntityMapper<User, UserInput, UserModel > {

    private final ModelMapper modelMapper;

    @Override
    public User inputToEntity(UserInput input) {
        return modelMapper.map(input, User.class);
    }

    @Override
    public UserModel entityToModel(User entity) {
        return modelMapper.map(entity, UserModel.class);
    }
}
