package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.TagsRequest;
import com.desafioFinal.DesafioFinal.dtos.TagsResponse;
import com.desafioFinal.DesafioFinal.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @PostMapping
    public ResponseEntity<TagsResponse> criarTag(@RequestBody TagsRequest request) {

        TagsResponse response = tagsService.criarTag(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/vincular")
    public ResponseEntity<TagsResponse> vincularTagAoProfessor(Long id_tag, Long id_professor) {

        TagsResponse response = tagsService.vincularTagAoProfessor(id_tag, id_professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TagsResponse> atualizarTag(@PathVariable Long id, @RequestBody TagsRequest request) {

        TagsResponse response = tagsService.atualizarTag(id, request);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TagsResponse> buscarTagPorId(@PathVariable Long id) {

        TagsResponse response = tagsService.buscarTagPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_PROFESSOR)")
    public ResponseEntity<List<TagsResponse>> listarTodasTags() {

        List<TagsResponse> list = tagsService.listarTodasTags();

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTagPorId(@PathVariable Long id) {

        tagsService.excluirTag(id);
        return ResponseEntity.noContent().build();

    }

}
