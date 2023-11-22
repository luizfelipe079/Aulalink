package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.HorariosDisponiveisRequest;
import com.desafioFinal.DesafioFinal.dtos.HorariosDisponiveisResponse;
import com.desafioFinal.DesafioFinal.dtos.ListHorariosDisponiveisRequest;
import com.desafioFinal.DesafioFinal.dtos.MultiplosHorariosDisponivesResponse;
import com.desafioFinal.DesafioFinal.services.HorariosDisponiveisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @PutMapping("/{id}")
    public ResponseEntity<HorariosDisponiveisResponse> atualizarHorarioDisponivel(@PathVariable Long id, @RequestBody HorariosDisponiveisRequest request) {

        HorariosDisponiveisResponse response = service.atualizarHorarioDisponivel(id, request);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping
    public ResponseEntity<List<HorariosDisponiveisResponse>> buscarTodosHorariosDisponiveis() {

        List<HorariosDisponiveisResponse> list = service.buscarTodosHorariosDisponiveis();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/horario-professor/{idProfessor}")
    public ResponseEntity<List<HorariosDisponiveisResponse>> buscarTodosHorariosDisponiveisDoProfessor(@PathVariable Long idProfessor) {

        List<HorariosDisponiveisResponse> list = service.buscarTodosHorariosDisponiveisDoProfessor(idProfessor);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<HorariosDisponiveisResponse> buscarHorarioDisponivelPorId(@PathVariable Long id) {

        HorariosDisponiveisResponse response = service.buscarHorarioDisponivelPorId(id);

        return ResponseEntity.ok().body(response);

    }

    @PostMapping("/{id_horario}")
    public ResponseEntity<?> HorarioPreenchido(@PathVariable Long id_horario) {

        service.HorarioPreenchido(id_horario);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirHorarioDisponivel(@PathVariable Long id) {

        service.excluirHorarioDisponivel(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
