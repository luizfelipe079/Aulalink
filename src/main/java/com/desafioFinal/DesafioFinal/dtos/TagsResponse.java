package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.models.enums.Nivel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagsResponse {

    private Long id;
    private String descricao;
    private Nivel nivel;
    @JsonIgnore
    private Professor professor;
}
