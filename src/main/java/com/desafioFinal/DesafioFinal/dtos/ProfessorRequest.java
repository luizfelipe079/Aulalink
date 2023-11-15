package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.enums.Role;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequest {

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private short idade;
    private String cpf;
    private String formacao;
//    @Lob
//    private byte[] imagem;

}
