package com.alugacarro.alugacarro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesAluguelDTO {

    private Integer codigo;
    private LocalDate incioAluguel;
    private LocalDate fimAluguel;
    private String nome;
    private String numeroTelefone;
    private String cpf;
    private String email;
    private String marca;
    private String modelo;
    private String tipo;
    private String cor;
    private int numeroDePortas;
    private String tipoCombustivel;
    private String anoFabricacao;
    private BigDecimal valorDiaria;
    private BigDecimal valorTotal;
    private String statusContrato;

}
