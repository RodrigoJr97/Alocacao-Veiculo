package com.alugacarro.alugacarro.service;

import com.alugacarro.alugacarro.domain.entity.Carro;

import java.util.List;
import java.util.Optional;

public interface CarroService {

    Carro salvar(Carro carro);

    List<Carro> lista();

    Optional<Carro> findCarroById(Integer id);

    List<Carro> carroPorTipo(String tipo);

    List<Carro> listaCarroDisponivel();

    List<Carro> listaCarroIndisponivel();

    void delete(Integer id);

    void update(Integer id, Carro carro);

}
