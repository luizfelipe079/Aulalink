package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.HorariosDisponiveisRequest;
import com.desafioFinal.DesafioFinal.dtos.HorariosDisponiveisResponse;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.HorariosDisponiveis;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.repositories.HorariosDisponiveisRepository;
import com.desafioFinal.DesafioFinal.repositories.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorariosDisponiveisService {

    @Autowired
    private HorariosDisponiveisRepository repository;

    @Autowired
    private ProfessorRepository repositoryProfessor;



    @Autowired
    private ModelMapper mapper;

    public HorariosDisponiveisResponse criarHorarioDisponivel(HorariosDisponiveisRequest request) {

        HorariosDisponiveis horarioDisponivel = new HorariosDisponiveis();

        Professor professor = repositoryProfessor.findById(request.getProfessor_id()).orElseThrow(() -> idNotFound(request.getProfessor_id()));

        horarioDisponivel.setData(request.getDataEHoraInicio());
        horarioDisponivel.setProfessor(professor);

        return mapper.map(repository.save(horarioDisponivel), HorariosDisponiveisResponse.class);

    }

    public HorariosDisponiveisResponse atualizarHorarioDisponivel(Long id, HorariosDisponiveisRequest request) {

        HorariosDisponiveis horarioDisponivel = repository.findById(id).orElseThrow(() -> idNotFound(id));

        BeanUtils.copyProperties(request, horarioDisponivel, "id");

        return mapper.map(repository.save(horarioDisponivel), HorariosDisponiveisResponse.class);

    }

    public HorariosDisponiveisResponse buscarHorarioDisponivelPorId(Long id) {

        HorariosDisponiveis horarioDisponivel = repository.findById(id).orElseThrow(() -> idNotFound(id));

        return mapper.map(horarioDisponivel, HorariosDisponiveisResponse.class);

    }

    public List<HorariosDisponiveisResponse> buscarTodosHorariosDisponiveisDoProfessor(Long idProfessor) {

        List<HorariosDisponiveis> lista = repository.findByProfessorId(idProfessor);

        List<HorariosDisponiveisResponse> list = lista.stream().map(HorariosDisponiveis -> mapper.map(HorariosDisponiveis, HorariosDisponiveisResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<HorariosDisponiveisResponse> buscarTodosHorariosDisponiveis() {

        List<HorariosDisponiveis> lista = repository.findAll();

        List<HorariosDisponiveisResponse> list = lista.stream().map(HorariosDisponiveis -> mapper.map(HorariosDisponiveis, HorariosDisponiveisResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public void excluirHorarioDisponivel(Long id) {

        HorariosDisponiveis horarioDisponivel = repository.findById(id).orElseThrow(() -> idNotFound(id));

        repository.delete(horarioDisponivel);

    }

    public void HorarioPreenchido(Long id) {

        HorariosDisponiveis horarioDisponivel = repository.findById(id).orElseThrow(() -> idNotFound(id));

        horarioDisponivel.setHorarioPreenchido(true);

        repository.save(horarioDisponivel);

    }


    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }

}
