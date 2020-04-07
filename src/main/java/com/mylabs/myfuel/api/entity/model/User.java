package com.mylabs.myfuel.api.entity.model;

import com.mylabs.myfuel.api.entity.enuns.RoleEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Size(min = 6 , max = 12, message = "A senha deve ter entre ${min} e no máximo ${max} caracteres")
    private String password;

    @Column(nullable = false, unique = true)
    @Email(message = "Informe email válido")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
