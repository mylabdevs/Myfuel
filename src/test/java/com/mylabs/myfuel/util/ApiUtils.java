package com.mylabs.myfuel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylabs.myfuel.domain.dto.user.UserInput;
import com.mylabs.myfuel.domain.dto.user.UserModel;
import com.mylabs.myfuel.domain.enuns.RoleEnum;
import com.mylabs.myfuel.domain.entity.User;

import java.time.LocalDate;

public class ApiUtils {

    public static UserModel createNewUserModel() {
        return UserModel.builder().id(1l).email("user@teste.com.br").name("userTeste").id(1L).build();
    }

    public static UserInput createNewUserInput() {
        return UserInput.builder().email("userrrr@teste.com.br").password("123456").name("userTeste").build();
    }

    public static User createUser() {
        return User.builder().id(1L).email("user@teste.com.br").password("123456").name("userTeste").role(RoleEnum.ROLE_USER).build();
    }

    public static String userDTOToJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(createNewUserInput());
    }

    public static String userDTOInvalidJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new UserModel());
    }

    public static String userDTOEmailInvalidJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(User.builder().email("userteste.com.br").password("123456").name("userTeste").build());
    }

    public static String userDTOPasswordlInvalidJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(User.builder().email("user@teste.com.br").password("123").name("userTeste").build());
    }

    public static User createNewUser() {
        return User.builder().id(1L).email("user@teste.com.br").name("userTeste").dataCadastro(LocalDate.now()).build();
    }
}
