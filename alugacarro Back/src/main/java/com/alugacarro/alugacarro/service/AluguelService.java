package com.alugacarro.alugacarro.service;

import com.alugacarro.alugacarro.domain.entity.Aluguel;
import com.alugacarro.alugacarro.domain.entity.Carro;
import com.alugacarro.alugacarro.dto.AluguelDTO;

import java.util.Optional;


public interface AluguelService {

    Aluguel salvar(AluguelDTO aluguelDTO);
    Optional<Aluguel> obterAluguelCompleto(Integer id);


}
