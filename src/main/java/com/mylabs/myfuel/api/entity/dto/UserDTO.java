package com.mylabs.myfuel.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "O nome deve ser informado")
    private String name;

    @NotEmpty(message = "O email deve ser informado")
    @Email(message = "Informe um email válido")
    private String email;

    @NotEmpty(message = "Informe uma senha")
    @Size(min = 6 , max = 12, message = "A senha deve ter entre {min} e no máximo {max} caracteres")
    private String password;

    private String role;

}
