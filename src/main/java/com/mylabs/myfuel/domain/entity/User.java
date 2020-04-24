package com.mylabs.myfuel.domain.entity;

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

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Size(min = 6 , max = 12)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
