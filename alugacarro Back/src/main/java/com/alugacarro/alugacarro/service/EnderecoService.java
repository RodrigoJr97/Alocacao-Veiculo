package com.alugacarro.alugacarro.service;

import com.alugacarro.alugacarro.domain.entity.EnderecoCliente;

import java.util.List;
import java.util.Optional;

public interface EnderecoService {

    EnderecoCliente salvar(EnderecoCliente enderecoCliente);

    List<EnderecoCliente> lista();

    Optional<EnderecoCliente> findEnderecoById(Integer id);

    void delete(Integer id);

    void update(Integer id, EnderecoCliente novoEndereco);

}
