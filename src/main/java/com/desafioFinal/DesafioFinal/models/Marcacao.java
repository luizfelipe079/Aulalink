package com.desafioFinal.DesafioFinal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date dataInicio;
    private Date dataTermino;
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Aluno aluno;

}
