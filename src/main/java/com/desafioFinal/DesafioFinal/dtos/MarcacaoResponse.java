package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacaoResponse {

    private Long id;
    private String descricao;
    private Date dataInicio;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date dataTermino;
    private Professor professor;
}
