package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.Tags;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponse {

    private Long id;
    private String nome;
    private String email;
    private List<Tags> tag;
}
