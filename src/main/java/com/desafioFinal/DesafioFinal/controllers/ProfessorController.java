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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorResponse> criarProfessor(@RequestBody @Valid ProfessorRequest request) {

        ProfessorResponse response = professorService.criarProfessor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponse> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorRequest request) {

        ProfessorResponse response = professorService.atualizarProfessor(id, request);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponse> buscarProfessorPorId(@PathVariable Long id) {

        ProfessorResponse response = professorService.buscarProfessorPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listarTodasTags() {

        List<ProfessorResponse> list = professorService.listarTodosProfessores();

        return ResponseEntity.ok().body(list);

    }

//    @GetMapping(path = "/{tags}")
//    public ResponseEntity<List<ProfessorResponse>> listarTodosProfessoresPorTags(@PathVariable String[] Tags) {
//
//        List<ProfessorResponse> list = professorService.listarTodosProfessoresPorTags(Tags);
//
//        return ResponseEntity.ok().body(list);
//
//    }

    @GetMapping(path = "/portags/{tag}")
    public ResponseEntity<List<ProfessorResponse>> listarTodosProfessoresPorTags(@PathVariable String tag) {

        List<ProfessorResponse> list = professorService.listarTodosProfessoresPorTags(tag);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping(path = "/proftags/{professor}")
    public ResponseEntity<ProfessorResponse> listarTodasTagsDoProfessor(@PathVariable String professor) {

        ProfessorResponse response = professorService.listarTodasTagsDoProfessor(professor);

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTagPorId(@PathVariable Long id) {

        professorService.excluirProfessor(id);
        return ResponseEntity.noContent().build();

    }

//    @GetMapping("/form")
//    public String form(Model model) {
//        model.addAttribute("professor", new Professor());
//        return "form";
//    }
//
//    @PostMapping("/upload")
//    public String handleFileUpload(@ModelAttribute ProfessorRequest professor, @RequestParam("file") MultipartFile file) {
//        try {
//            professor.setImagem(file.getBytes());
//            professorService.criarProfessor(professor);
//            return "redirect:/form?success";
//        } catch (IOException e) {
//            return "redirect:/form?error";
//        }
//    }
}


