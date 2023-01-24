package com.alugacarro.alugacarro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Nome do Usuario Obrigatorio")
    @Column(nullable = false, unique = true)
    private String nomeUsuario;

    @NotEmpty(message = "Senha do Usuario Obrigatorio")
    @Column(nullable = false)
    private String senha;

    @Column
    private boolean admin;


}
