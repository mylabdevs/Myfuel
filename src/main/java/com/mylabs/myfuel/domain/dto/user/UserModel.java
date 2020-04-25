package com.mylabs.myfuel.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long id;

    private String name;

    private String email;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataCadastro;
}
