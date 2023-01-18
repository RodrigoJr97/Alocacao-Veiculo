package com.alugacarro.alugacarro.service;

import java.util.List;
import java.util.Optional;

public interface CrudServiceGenerico<T>{

    T salvar(T t);

    List<T> lista();

    Optional<T> findById(Integer id);

    void delete(Integer id);

    void update(Integer id, T t);

}
