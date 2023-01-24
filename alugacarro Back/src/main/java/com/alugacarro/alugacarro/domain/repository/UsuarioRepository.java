package com.alugacarro.alugacarro.domain.repository;

import com.alugacarro.alugacarro.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);

}
