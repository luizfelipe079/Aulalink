package com.desafioFinal.DesafioFinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacaoVinculaProfEAluRequest {

    private Long id_marcacao;
    private Long id_professor;
    private Long id_aluno;

}
