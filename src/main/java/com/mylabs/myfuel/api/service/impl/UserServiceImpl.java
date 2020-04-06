package com.mylabs.myfuel.api.service.impl;

import com.mylabs.myfuel.api.entity.model.User;
import com.mylabs.myfuel.api.repository.UserRepository;
import com.mylabs.myfuel.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
