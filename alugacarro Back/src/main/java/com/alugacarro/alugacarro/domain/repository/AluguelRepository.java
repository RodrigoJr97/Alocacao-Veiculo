package com.alugacarro.alugacarro.domain.repository;

import com.alugacarro.alugacarro.domain.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

//    @Query("select carro from aluguel where aluguel.id = :id")
//    Optional<Aluguel> findByAluguel(@Param("id") Integer id);

}
