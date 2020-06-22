package com.carlosfunalleras.rest.exceptions;

public class UsuarioNotFoundException extends Exception {

	private static final long serialVersionUID = -6104414723619151599L;
	
	public UsuarioNotFoundException() {
        super();
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }

}
