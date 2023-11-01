package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequest {

    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private Role role;

}
