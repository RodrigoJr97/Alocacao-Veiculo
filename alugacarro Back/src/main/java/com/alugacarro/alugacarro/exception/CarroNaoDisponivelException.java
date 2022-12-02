package com.alugacarro.alugacarro.exception;

public class CarroNaoDisponivelException extends RuntimeException{
    public CarroNaoDisponivelException() {
        super("Carro não está disponível no momento.");
    }
}
