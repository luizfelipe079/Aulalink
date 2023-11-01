package com.desafioFinal.DesafioFinal.dtos;

import com.desafioFinal.DesafioFinal.models.enums.Nivel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagsRequest {

    @NotEmpty(message = "Campo descrição é obrigatório")
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Nivel nivel;
}
