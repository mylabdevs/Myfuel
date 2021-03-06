package com.mylabs.myfuel.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mylabs.myfuel.domain.enuns.RoleEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Usuario implements Serializable {

    private static final long serialVersionUID = 6018260411024315925L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
