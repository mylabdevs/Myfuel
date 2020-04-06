package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.api.entity.dto.UserDTO;
import com.mylabs.myfuel.api.entity.dto.UserResDTO;
import com.mylabs.myfuel.api.entity.enuns.RoleEnum;
import com.mylabs.myfuel.api.entity.model.User;
import com.mylabs.myfuel.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService service;

    private final ModelMapper modelMapper;

    public UserController(UserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<UserResDTO> save(@RequestBody @Valid UserDTO userDTO) {

        User user = modelMapper.map(userDTO, User.class);

        user = service.save(user);

        UserResDTO response = modelMapper.map(user, UserResDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
