package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.mapper.UserMapper;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "User Rotas", tags = {"user"})
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    private final UserMapper userMapper;

    public UserController(UserService service, UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "Salvar usuário")
    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody @Valid  UserInput userInput) {
        User user = userMapper.toEntity(userInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toModel(service.save(user)));
    }

}
