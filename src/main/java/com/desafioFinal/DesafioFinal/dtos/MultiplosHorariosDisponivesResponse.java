package com.desafioFinal.DesafioFinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiplosHorariosDisponivesResponse {

    private List<HorariosDisponiveisResponse> response;
}
