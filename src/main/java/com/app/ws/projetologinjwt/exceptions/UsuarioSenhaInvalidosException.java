package com.app.ws.projetologinjwt.exceptions;

import org.springframework.http.HttpStatus;

public class UsuarioSenhaInvalidosException extends RuntimeException {

    /**
     * - Exception para informar que o usuário e/ou a senha estão inválidos
     */
    private static final long serialVersionUID = 1L;
    public static final String MSG = "Usuário e/ou senha inválidos";

    public UsuarioSenhaInvalidosException(HttpStatus httpStatus) {

        super(MSG);
    }
}
