package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.ProfessorRequest;
import com.desafioFinal.DesafioFinal.dtos.ProfessorResponse;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.repositories.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {


    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ModelMapper mapper;

    public ProfessorResponse criarProfessor(ProfessorRequest request) {

        Professor professor = new Professor();

        BeanUtils.copyProperties(request, professor);

        return mapper.map(professorRepository.save(professor), ProfessorResponse.class);


    }


    public ProfessorResponse atualizarProfessor(Long id, ProfessorRequest request) {

        Professor professor = professorRepository.findById(id).orElseThrow(() -> idNotFound(id));
        BeanUtils.copyProperties(request, professor, "id");
        return mapper.map(professorRepository.save(professor), ProfessorResponse.class);

    }

    public ProfessorResponse buscarProfessorPorId(Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> idNotFound(id));
        return mapper.map(professor, ProfessorResponse.class);
    }

    public List<ProfessorResponse> listarTodosProfessores() {
        List<Professor> lista = professorRepository.findAll();

        List<ProfessorResponse> list = lista.stream().map(Professor -> mapper.map(Professor, ProfessorResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public void excluirProfessor(Long id) {

        Professor professor = professorRepository.findById(id).orElseThrow(() -> idNotFound(id));
        professorRepository.delete(professor);

    }

    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }

}
