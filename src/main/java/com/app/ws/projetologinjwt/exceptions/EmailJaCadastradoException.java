package com.app.ws.projetologinjwt.exceptions;

public class EmailJaCadastradoException extends RuntimeException {
	
	/**
	 * - Exception para informar que o email já existe na base
	 */
	private static final long serialVersionUID = 1L;
	public static final String MSG = "E-mail já existente";

	public EmailJaCadastradoException() {

		super(MSG);
	}
}
