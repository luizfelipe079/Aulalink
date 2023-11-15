package com.desafioFinal.DesafioFinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private String nome;
    private String email;
    private short idade;

}
