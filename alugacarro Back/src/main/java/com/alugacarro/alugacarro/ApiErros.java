package com.alugacarro.alugacarro;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(List<String> mensagemErro) {
        this.erros = new ArrayList<>(mensagemErro);
    }
}
