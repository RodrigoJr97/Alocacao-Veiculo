package com.alugacarro.alugacarro.domain.repository;

import com.alugacarro.alugacarro.domain.entity.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Integer> {
}
