package com.alugacarro.alugacarro.service;

import com.alugacarro.alugacarro.domain.entity.Carro;

import java.util.List;

public interface CarroService extends CrudServiceGenerico<Carro>{

    List<Carro> carroPorTipo(String tipo);

    List<Carro> listaCarroDisponivel();

    List<Carro> listaCarroIndisponivel();
    
}
