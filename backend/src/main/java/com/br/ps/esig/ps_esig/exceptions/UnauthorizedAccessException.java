package com.br.ps.esig.ps_esig.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException() {
        super("O usuário está tentando acessar dados não autorizados");
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}