package com.desafioFinal.DesafioFinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professor_tags",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    @JsonIgnore
    private List<Tags> tag;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore(value = true)
    private List<Marcacao> marcacao;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<HorariosDisponiveis> horariosDisponiveis;

}
