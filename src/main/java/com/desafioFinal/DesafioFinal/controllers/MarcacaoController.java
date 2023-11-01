package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.MarcacaoRequest;
import com.desafioFinal.DesafioFinal.dtos.MarcacaoResponse;
import com.desafioFinal.DesafioFinal.services.MarcacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcacao")
public class MarcacaoController {

    @Autowired
    private MarcacaoService marcacaoService;

    @PostMapping
    public ResponseEntity<MarcacaoResponse> criarMarcacao(@RequestBody @Valid MarcacaoRequest request) {

        MarcacaoResponse response = marcacaoService.criarMarcacao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MarcacaoResponse> atualizarMarcacao(@PathVariable Long id, @RequestBody MarcacaoRequest request) {

        MarcacaoResponse response = marcacaoService.atualizarMarcacao(id, request);
        return ResponseEntity.ok().body(response);

    }

    @PostMapping("/vincular")
    public ResponseEntity<MarcacaoResponse> vincularMarcacaoAoProfessor(Long id_marcacao, Long id_professor) {

        MarcacaoResponse response = marcacaoService.vincularMarcacaoAoProfessor(id_marcacao, id_professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MarcacaoResponse> buscarMarcacaoPorId(@PathVariable Long id) {

        MarcacaoResponse response = marcacaoService.buscarMarcacaoPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<MarcacaoResponse>> listarTodasMarcacoes() {

        List<MarcacaoResponse> list = marcacaoService.listarTodasMarcacoes();

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> excluirMarcacaoPorId(@PathVariable Long id) {

        marcacaoService.excluirMarcacao(id);
        return ResponseEntity.noContent().build();

    }

}