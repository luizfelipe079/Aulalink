package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.*;
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

    @PutMapping
    public ResponseEntity<MarcacaoResponse> atualizarMarcacao(@RequestParam Long id_marcacao, @RequestBody MarcacaoRequest request) {

        MarcacaoResponse response = marcacaoService.atualizarMarcacao(id_marcacao, request);
        return ResponseEntity.ok().body(response);

    }

    @PutMapping("/vincula-prof")
    public ResponseEntity<MarcacaoResponse> vincularMarcacaoAoProfessor(@RequestBody MarcacaoVinculaProfessorRequest request) {

        MarcacaoResponse response = marcacaoService.vincularMarcacaoAoProfessor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/vincula-aluno")
    public ResponseEntity<MarcacaoResponse> vincularMarcacaoAoAluno(@RequestBody MarcacaoVinculaAlunoRequest request) {

        MarcacaoResponse response = marcacaoService.vincularMarcacaoAoAluno(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/multiplas-marcacoes")
    public ResponseEntity<List<MarcacaoResponse>> criarMultiplasMarcacoesDisponiveis(@RequestBody ListMarcacoesDisponiveisRequest request){

        List<MarcacaoResponse> list = marcacaoService.criarMultiplasMarcacoesDisponiveis(request);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/id")
    public ResponseEntity<MarcacaoResponse> buscarMarcacaoPorId(@RequestParam  Long id) {

        MarcacaoResponse response = marcacaoService.buscarMarcacaoPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<MarcacaoResponse>> listarTodasMarcacoes() {

        List<MarcacaoResponse> list = marcacaoService.listarTodasMarcacoes();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/professor-id")
    public ResponseEntity<List<MarcacaoResponse>> listarTodasMarcacoesPorProfessor(@RequestParam  Long id_professor) {

        List<MarcacaoResponse> list = marcacaoService.listarTodasMarcacoesPorProfessor(id_professor);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/aluno-id")
    public ResponseEntity<List<MarcacaoResponse>> listarTodasMarcacoesPorAluno(@RequestParam  Long id_aluno) {

        List<MarcacaoResponse> list = marcacaoService.listarTodasMarcacoesPorAluno(id_aluno);

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping
    public ResponseEntity<?> excluirMarcacaoPorId(@RequestBody IdRequest request) {

        marcacaoService.excluirMarcacao(request);
        return ResponseEntity.ok().build();

    }

}