package com.desafioFinal.DesafioFinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tags> tag;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore(value = true)
    private List<Marcacao> marcacao;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<HorariosDisponiveis> horariosDisponiveis;

}
