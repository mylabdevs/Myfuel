package com.mylabs.myfuel.infraestrutura.service;

import com.mylabs.myfuel.domain.dto.mapper.UserMapper;
import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.enuns.RoleEnum;
import com.mylabs.myfuel.domain.exception.NegocioException;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CrudUserService implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public CrudUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserModel save(UserInput userInput) {

        if (userRepository.existsByEmail(userInput.getEmail())) {
            throw new NegocioException("Já existe usuário cadastrado com este e-mail: " + userInput.getEmail());
        }
        User user = userMapper.inputToEntity(userInput);

        user.setDataCadastro(LocalDate.now());
        user.setRole(RoleEnum.ROLE_USER);

        user = userRepository.save(user);

        return userMapper.entityToModel(user);
    }
}
