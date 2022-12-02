package com.alugacarro.alugacarro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AluguelDTO {

    @NotNull(message = "Campo Obrigatório - Id Cliente")
    private Integer cliente;

    @NotNull(message = "Campo Obrigatório - Id Carro")
    private Integer carro;

    @NotNull(message = "Campo Obrigatório - Quantos dias de alocação")
    private Integer diasDeAluguel;

}
