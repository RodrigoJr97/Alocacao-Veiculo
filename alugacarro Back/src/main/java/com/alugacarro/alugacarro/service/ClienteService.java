package com.alugacarro.alugacarro.service;

import com.alugacarro.alugacarro.domain.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    List<Cliente> lista();

    Optional<Cliente> findClienteById(Integer id);

    void delete(Integer id);

}
