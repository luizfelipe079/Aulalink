package com.desafioFinal.DesafioFinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Professor extends Usuario {

    private String formacao;
    @Lob
    private byte[] imagem;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<Tags> tag;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<Marcacao> marcacao;
    @OneToMany(mappedBy = "professor")
    private List<HorariosDisponiveis> horariosDisponiveis;

}
