package com.desafioFinal.DesafioFinal.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marcacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @NotNull
    private Date dataInicio;
    @NotNull
    private Date dataTermino;
    private boolean horarioPreenchido;
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Aluno aluno;

}
