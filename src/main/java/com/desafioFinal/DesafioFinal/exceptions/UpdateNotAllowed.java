package com.desafioFinal.DesafioFinal.exceptions;

public class UpdateNotAllowed extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UpdateNotAllowed(String message) {
        super(message);
    }

}
