package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.ProfessorRequest;
import com.desafioFinal.DesafioFinal.dtos.ProfessorResponse;
import com.desafioFinal.DesafioFinal.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorResponse> criarAluno(@RequestBody @Valid ProfessorRequest request) {

        ProfessorResponse response = professorService.criarProfessor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponse> atualizarAluno(@PathVariable Long id, @RequestBody ProfessorRequest request) {

        ProfessorResponse response = professorService.atualizarProfessor(id, request);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponse> buscarAlunoPorId(@PathVariable Long id) {

        ProfessorResponse response = professorService.buscarProfessorPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listarTodasTags() {

        List<ProfessorResponse> list = professorService.listarTodosProfessores();

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTagPorId(@PathVariable Long id) {

        professorService.excluirProfessor(id);
        return ResponseEntity.noContent().build();

    }

}
