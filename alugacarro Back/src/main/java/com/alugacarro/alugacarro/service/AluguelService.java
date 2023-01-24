package com.alugacarro.alugacarro.service;

import com.alugacarro.alugacarro.domain.entity.Aluguel;
import com.alugacarro.alugacarro.dto.AluguelDTO;
import com.alugacarro.alugacarro.dto.InformacoesAluguelDTO;

import java.util.Optional;


public interface AluguelService {

    Aluguel salvar(AluguelDTO aluguelDTO);

    Optional<Aluguel> findById(Integer id);

    void finalizaAluguel(Integer id);

    void delete(Integer id);

    InformacoesAluguelDTO converter(Aluguel aluguel);

}
