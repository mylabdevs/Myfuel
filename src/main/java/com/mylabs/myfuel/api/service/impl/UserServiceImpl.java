package com.mylabs.myfuel.api.service.impl;

import com.mylabs.myfuel.api.entity.model.User;
import com.mylabs.myfuel.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User save(User user) {
        return User.builder().id(1L).build();
    }
}
