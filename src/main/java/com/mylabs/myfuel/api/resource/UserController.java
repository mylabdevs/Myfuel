package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.mapper.UserMapper;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.entity.Usuario;
import com.mylabs.myfuel.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "User Rotas", tags = {"users"})
@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService service, UserMapper userMapper) {
        this.userService = service;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "Salvar usuário")
    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody @Valid  UserInput userInput) {
        Usuario usuario = userMapper.toEntity(userInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toModel(userService.save(usuario)));
    }

}
