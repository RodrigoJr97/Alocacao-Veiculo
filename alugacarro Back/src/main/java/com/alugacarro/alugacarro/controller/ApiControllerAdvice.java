package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.ApiErros;
import com.alugacarro.alugacarro.exception.CarroNaoDisponivelException;
import com.alugacarro.alugacarro.exception.ClienteComContratoAbertoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handlerValidacaoException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map( erro -> erro.getDefaultMessage() )
                .collect(Collectors.toList());

        return new ApiErros(erros);
    }

    @ExceptionHandler(CarroNaoDisponivelException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handlerCarroNaoDisponivelException(CarroNaoDisponivelException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErros(Collections.singletonList(mensagemErro));
    }

    @ExceptionHandler(ClienteComContratoAbertoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handlerClienteComContratoAberto(ClienteComContratoAbertoException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErros(Collections.singletonList(mensagemErro));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros hanlderCpfCadastrado(DataIntegrityViolationException ex) {
        String erros = "Já existe cadastro com um dos campos: CPF, Email ou Telefone";

        return new ApiErros(Collections.singletonList(erros));
    }

}
