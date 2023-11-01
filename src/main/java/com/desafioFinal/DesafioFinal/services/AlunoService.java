package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.AlunoRequest;
import com.desafioFinal.DesafioFinal.dtos.AlunoResponse;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.Aluno;
import com.desafioFinal.DesafioFinal.repositories.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ModelMapper mapper;

    public AlunoResponse criarAluno(AlunoRequest request) {

        Aluno aluno = new Aluno();

        BeanUtils.copyProperties(request, aluno);

        return mapper.map(alunoRepository.save(aluno), AlunoResponse.class);


    }


    public AlunoResponse atualizarAluno(Long id, AlunoRequest request) {

        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        BeanUtils.copyProperties(request, aluno, "id");
        return mapper.map(alunoRepository.save(aluno), AlunoResponse.class);

    }

    public AlunoResponse buscarAlunoPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        return mapper.map(aluno, AlunoResponse.class);
    }

    public List<AlunoResponse> listarTodosAlunos() {
        List<Aluno> lista = alunoRepository.findAll();

        List<AlunoResponse> list = lista.stream().map(Aluno -> mapper.map(Aluno, AlunoResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public void excluirAluno(Long id) {

        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        alunoRepository.delete(aluno);

    }

    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }
}
