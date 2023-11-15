package com.desafioFinal.DesafioFinal.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Aluno extends Usuario {

    private String educacao;

}
