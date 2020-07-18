package com.mylabs.myfuel.infraestrutura.service;

import com.mylabs.myfuel.domain.entity.Usuario;
import com.mylabs.myfuel.domain.enuns.RoleEnum;
import com.mylabs.myfuel.domain.exception.NegocioException;
import com.mylabs.myfuel.domain.repository.UserRepository;
import com.mylabs.myfuel.domain.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CrudUserService implements UserService {

    private final UserRepository userRepository;

    public CrudUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {

        if (userRepository.existsByEmail(usuario.getEmail())) {
            throw new NegocioException("Já existe usuário cadastrado com este e-mail: " + usuario.getEmail());
        }
        usuario.setDataCadastro(LocalDate.now());
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuario.setRole(RoleEnum.ROLE_USER);

        return userRepository.save(usuario);
    }
}
