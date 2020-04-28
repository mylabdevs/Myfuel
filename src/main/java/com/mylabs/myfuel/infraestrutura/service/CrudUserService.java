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

    public CrudUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new NegocioException("Já existe usuário cadastrado com este e-mail: " + user.getEmail());
        }
        user.setDataCadastro(LocalDate.now());
        user.setRole(RoleEnum.ROLE_USER);

        return userRepository.save(user);
    }
}
