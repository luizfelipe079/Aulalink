package com.desafioFinal.DesafioFinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListHorariosDisponiveisRequest {

    private Long idProfessor;
    private List<Date> ListdataEHoraInicio;
}
