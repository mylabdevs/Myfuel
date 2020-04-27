package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;

public interface UserService {

    UserModel save(UserInput userInput);
}
