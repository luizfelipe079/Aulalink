package com.desafioFinal.DesafioFinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Aluno extends Usuario {

    private String educacao;
    @OneToMany(mappedBy = "aluno")
    @JsonIgnore(value = true)
    private List<Marcacao> marcacao;

}
