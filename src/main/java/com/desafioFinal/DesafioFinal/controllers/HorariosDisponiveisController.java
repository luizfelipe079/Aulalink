package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.HorariosDisponiveisRequest;
import com.desafioFinal.DesafioFinal.dtos.HorariosDisponiveisResponse;
import com.desafioFinal.DesafioFinal.dtos.IdRequest;
import com.desafioFinal.DesafioFinal.dtos.ListHorariosDisponiveisRequest;
import com.desafioFinal.DesafioFinal.services.HorariosDisponiveisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/horario-disponivel")
public class HorariosDisponiveisController {

    @Autowired
    private HorariosDisponiveisService service;

    @PostMapping
    public ResponseEntity<HorariosDisponiveisResponse> criarHorarioDisponivel(@RequestBody HorariosDisponiveisRequest request) {

        HorariosDisponiveisResponse response = service.criarHorarioDisponivel(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/multiplos")
    public ResponseEntity<List<HorariosDisponiveisResponse>> criarMultiplosHorariosDisponiveis(@RequestBody ListHorariosDisponiveisRequest request) {

        List<HorariosDisponiveisResponse> list = service.criarMultiplosHorariosDisponiveis(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @PutMapping
    public ResponseEntity<HorariosDisponiveisResponse> atualizarHorarioDisponivel(@RequestParam Long id, @RequestBody HorariosDisponiveisRequest request) {

        HorariosDisponiveisResponse response = service.atualizarHorarioDisponivel(id, request);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<HorariosDisponiveisResponse>> buscarTodosHorariosDisponiveis() {

        List<HorariosDisponiveisResponse> list = service.buscarTodosHorariosDisponiveis();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/horario-professor-id")
    public ResponseEntity<List<HorariosDisponiveisResponse>> buscarTodosHorariosDisponiveisDoProfessor(@RequestParam  Long idProfessor) {

        List<HorariosDisponiveisResponse> list = service.buscarTodosHorariosDisponiveisDoProfessor(idProfessor);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/horario-id")
    public ResponseEntity<HorariosDisponiveisResponse> buscarHorarioDisponivelPorId(@RequestParam  Long id) {

        HorariosDisponiveisResponse response = service.buscarHorarioDisponivelPorId(id);

        return ResponseEntity.ok().body(response);

    }

    @PostMapping("/horario-preenchido-id")
    public ResponseEntity<?> HorarioPreenchido(@RequestParam  Long id_horario) {

        service.HorarioPreenchido(id_horario);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping
    public ResponseEntity<?> excluirHorarioDisponivel(@RequestBody IdRequest request) {

        service.excluirHorarioDisponivel(request);
        return ResponseEntity.ok().build();

    }

}
