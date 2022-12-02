package com.alugacarro.alugacarro.domain.repository;

import com.alugacarro.alugacarro.domain.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    @Query("SELECT car FROM Carro car WHERE UPPER(tipo) LIKE UPPER(?1)")
    List<Carro> findCarroByTipo(@Param("tipo") String tipo);

    @Query("SELECT car FROM Carro car WHERE disponivel= true")
    List<Carro> findByCarroDisponivel();

    @Query("SELECT car FROM Carro car WHERE disponivel= false")
    List<Carro> findByCarroIndisponivel();


}
