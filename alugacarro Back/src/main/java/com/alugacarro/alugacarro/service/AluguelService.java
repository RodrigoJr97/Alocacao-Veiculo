package com.alugacarro.alugacarro.service;

import com.alugacarro.alugacarro.domain.entity.Aluguel;
import com.alugacarro.alugacarro.domain.entity.Carro;
import com.alugacarro.alugacarro.dto.AluguelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface AluguelService {

    Aluguel salvar(AluguelDTO aluguelDTO);
    Optional<Aluguel> obterAluguelCompleto(Integer id);

    void finalizaAluguel(Integer id);

}
