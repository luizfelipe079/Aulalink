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
    private String senha;
    private String email;
    private String cpf;
    private String dificuldade;
    private Role role;


}
