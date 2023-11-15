package com.desafioFinal.DesafioFinal.exceptions;

public class AutenticacaoException extends RuntimeException{

    public AutenticacaoException(){
        super("Erro ao tentar autenticar!");
    }
}
