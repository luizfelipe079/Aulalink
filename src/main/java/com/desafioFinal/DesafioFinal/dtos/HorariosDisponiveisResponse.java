package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorariosDisponiveisResponse {

    private Long id;
    private Date data;
    private ProfessorResponse professor;
    private boolean horarioPreenchido;

}
