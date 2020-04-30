package com.mylabs.myfuel.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "abastecimento")
public class Abastecimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "posto", nullable = false)
    private String posto;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "preco_combustivel", precision = 10, scale = 2)
    private BigDecimal precoCombustivel;

    @Column(name = "km_atual")
    private BigDecimal kmAtual;

    @ManyToOne
    private Veiculo veiculo;
}
