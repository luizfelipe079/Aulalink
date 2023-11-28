package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.IdRequest;
import com.desafioFinal.DesafioFinal.dtos.TagVinculaRequest;
import com.desafioFinal.DesafioFinal.dtos.TagsRequest;
import com.desafioFinal.DesafioFinal.dtos.TagsResponse;
import com.desafioFinal.DesafioFinal.services.TagsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tags")
@Tag(name = "Tags COntroller", description = "API com todas as funções da Tag")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @PostMapping
    public ResponseEntity<TagsResponse> criarTag(@RequestBody TagsRequest request) {

        TagsResponse response = tagsService.criarTag(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/vincular")
    public ResponseEntity<TagsResponse> vincularTagAoProfessor(@RequestBody TagVinculaRequest request) {

        TagsResponse response = tagsService.vincularTagAoProfessor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TagsResponse> atualizarTag(@RequestParam Long id, @RequestBody TagsRequest request) {

        TagsResponse response = tagsService.atualizarTag(id, request);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("tag-id")
    public ResponseEntity<TagsResponse> buscarTagPorId(@RequestParam  Long id) {

        TagsResponse response = tagsService.buscarTagPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<TagsResponse>> listarTodasTags() {

        List<TagsResponse> list = tagsService.listarTodasTags();

        return ResponseEntity.ok().body(list);

    }

    @DeleteMapping
    public ResponseEntity<?> excluirTagPorId(@RequestBody IdRequest request) {

        tagsService.excluirTag(request);
        return ResponseEntity.ok().build();

    }

}
