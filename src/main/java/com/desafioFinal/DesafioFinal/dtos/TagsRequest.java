package com.desafioFinal.DesafioFinal.dtos;

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
}
