package com.alugacarro.alugacarro.exception;

public class ClienteComContratoAbertoException extends RuntimeException {
    public ClienteComContratoAbertoException() {
        super("Cliente está com um contrato em andamento.");
    }
}
