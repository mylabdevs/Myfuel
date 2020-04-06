package com.mylabs.myfuel.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylabs.myfuel.api.entity.dto.UserDTO;
import com.mylabs.myfuel.api.entity.enuns.RoleEnum;
import com.mylabs.myfuel.api.entity.model.User;

import java.time.LocalDate;

public class ApiUtils {


    public static UserDTO createNewUserDTO() {
        return UserDTO.builder().email("user@teste.com.br").password("123456").name("userTeste").role("ROLE_USER").build();
    }

    public static User createNewUser() {
        return User.builder().id(1L).email("user@teste.com.br").password("123456").name("userTeste").role(RoleEnum.ROLE_USER).build();
    }

    public static String userDTOToJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(createNewUserDTO());
    }

    public static String userDTOInvalidJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new UserDTO());
    }

    public static String userDTOEmailInvalidJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(UserDTO.builder().email("userteste.com.br").password("123456").name("userTeste").role("ROLE_USER").build());
    }

    public static String userDTOPasswordlInvalidJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(UserDTO.builder().email("user@teste.com.br").password("123").name("userTeste").role("ROLE_USER").build());
    }
}
