package com.app.ws.projetologinjwt.exceptions;

public class NaoAutorizadoException extends RuntimeException {

    /**
     * - Exception para informar que o acesso não foi autorizado devido ao Token Inexistente
     */
    private static final long serialVersionUID = 1L;
    public static final String MSG = "Não autorizado";

    public NaoAutorizadoException() {

        super(MSG);
    }


}
