package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.AlunoRequest;
import com.desafioFinal.DesafioFinal.dtos.AlunoResponse;
import com.desafioFinal.DesafioFinal.dtos.IdRequest;
import com.desafioFinal.DesafioFinal.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> criarAluno(@RequestBody @Valid AlunoRequest request) {

        AlunoResponse response = alunoService.criarAluno(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @PutMapping
    public ResponseEntity<AlunoResponse> atualizarAluno(@RequestParam Long id, @RequestBody AlunoRequest request) {

        AlunoResponse response = alunoService.atualizarAluno(id, request);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("aluno-id")
    public ResponseEntity<AlunoResponse> buscarAlunoPorId(@RequestParam  Long id) {

        AlunoResponse response = alunoService.buscarAlunoPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodasTags() {


        List<AlunoResponse> list = alunoService.listarTodosAlunos();

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping
    public ResponseEntity<?> excluirTagPorId(@RequestBody IdRequest request) {

        alunoService.excluirAluno(request);
        return ResponseEntity.ok().build();

    }

}
