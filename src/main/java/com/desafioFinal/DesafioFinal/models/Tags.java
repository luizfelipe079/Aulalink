package com.desafioFinal.DesafioFinal.models;

import com.desafioFinal.DesafioFinal.models.enums.Nivel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Nivel nivel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Professor professor;
}
