package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequest {

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private short idade;
    private String cpf;
    private String educacao;


}
