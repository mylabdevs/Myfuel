package com.mylabs.myfuel.security;

import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOpt = userRepository.findByEmail(username);

        if (!userOpt.isPresent())
            throw new UsernameNotFoundException("Usuário ou senha inválidos");

        return new ApiUserDetails(userOpt.get());
    }
}
