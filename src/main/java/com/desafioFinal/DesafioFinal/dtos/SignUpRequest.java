package com.desafioFinal.DesafioFinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private short idade;
}
