package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "User Rotas", tags = {"user"})
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    private final ModelMapper modelMapper;

    public UserController(UserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Salvar usuário")
    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody @Valid UserInput userInput) {

        User user = toEntity(userInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(service.save(user)));

    }

    private UserModel toModel(User user) {
        return modelMapper.map(user, UserModel.class);
    }

    private User toEntity(UserInput userInput) {
        return modelMapper.map(userInput, User.class);
    }
}
