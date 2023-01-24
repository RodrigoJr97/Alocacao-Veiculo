package com.alugacarro.alugacarro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private Integer id;
    private String nomeUsuario;
    private String token;

}
