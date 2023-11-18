package com.desafioFinal.DesafioFinal.models;

import com.desafioFinal.DesafioFinal.models.enums.Nivel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ManyToMany(mappedBy = "tag",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Professor> professor;
}
