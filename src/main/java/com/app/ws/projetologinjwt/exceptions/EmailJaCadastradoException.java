package com.app.ws.projetologinjwt.exceptions;

public class EmailJaCadastradoException extends RuntimeException {
	
	/**
	 * - Exception para informar que o email já existe na base
	 */
	private static final long serialVersionUID = 1L;

	public EmailJaCadastradoException(String msg) {
		super(msg);
	}

}
