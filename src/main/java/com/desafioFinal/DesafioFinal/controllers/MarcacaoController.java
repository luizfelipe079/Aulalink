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
    public ResponseEntity<MarcacaoResponse> vincularMarcacaoAoProfessorEAluno(Long id_marcacao, Long id_professor, Long id_aluno) {

        MarcacaoResponse response = marcacaoService.vincularMarcacaoAoProfessorEAluno(id_marcacao, id_professor, id_aluno);
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

    @GetMapping("/professor/{id_professor}")
    public ResponseEntity<List<MarcacaoResponse>> listarTodasMarcacoesPorProfessor(@PathVariable Long id_professor) {

        List<MarcacaoResponse> list = marcacaoService.listarTodasMarcacoesPorProfessor(id_professor);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/aluno/{id_aluno}")
    public ResponseEntity<List<MarcacaoResponse>> listarTodasMarcacoesPorAluno(@PathVariable Long id_aluno) {

        List<MarcacaoResponse> list = marcacaoService.listarTodasMarcacoesPorAluno(id_aluno);

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> excluirMarcacaoPorId(@PathVariable Long id) {

        marcacaoService.excluirMarcacao(id);
        return ResponseEntity.noContent().build();

    }

}