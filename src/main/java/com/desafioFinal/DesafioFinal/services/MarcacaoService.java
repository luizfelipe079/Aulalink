package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.MarcacaoRequest;
import com.desafioFinal.DesafioFinal.dtos.MarcacaoResponse;
import com.desafioFinal.DesafioFinal.dtos.MarcacaoVinculaProfEAluRequest;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.Aluno;
import com.desafioFinal.DesafioFinal.models.Marcacao;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.repositories.AlunoRepository;
import com.desafioFinal.DesafioFinal.repositories.MarcacaoRepository;
import com.desafioFinal.DesafioFinal.repositories.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcacaoService {

    @Autowired
    private MarcacaoRepository marcacaoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ModelMapper mapper;

    public MarcacaoResponse criarMarcacao(MarcacaoRequest request) {

        Marcacao marc = new Marcacao();

        BeanUtils.copyProperties(request, marc);

        return mapper.map(marcacaoRepository.save(marc), MarcacaoResponse.class);


    }

    public MarcacaoResponse vincularMarcacaoAoProfessorEAluno(MarcacaoVinculaProfEAluRequest request) {

        Marcacao marca = marcacaoRepository.findById(request.getId_marcacao()).orElseThrow(() -> idNotFound(request.getId_marcacao()));
        Professor prof = professorRepository.findById(request.getId_professor()).orElseThrow(() -> idNotFound(request.getId_professor()));
        Aluno aluno = alunoRepository.findById(request.getId_aluno()).orElseThrow(() -> idNotFound(request.getId_aluno()));

        marca.setProfessor(prof);
        marca.setAluno(aluno);

        return mapper.map(marcacaoRepository.save(marca), MarcacaoResponse.class);

    }

    public MarcacaoResponse atualizarMarcacao(Long id, MarcacaoRequest request) {

        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        BeanUtils.copyProperties(request, marc, "id");
        return mapper.map(marcacaoRepository.save(marc), MarcacaoResponse.class);

    }

    public MarcacaoResponse buscarMarcacaoPorId(Long id) {
        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        return mapper.map(marc, MarcacaoResponse.class);
    }

    public List<MarcacaoResponse> listarTodasMarcacoes() {
        List<Marcacao> lista = marcacaoRepository.findAll();

        List<MarcacaoResponse> list = lista.stream().map(Marcacao -> mapper.map(Marcacao, MarcacaoResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public void excluirMarcacao(Long id) {

        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        marcacaoRepository.delete(marc);

    }

    public List<MarcacaoResponse> listarTodasMarcacoesPorProfessor(Long idProfessor) {

       Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> idNotFound(idProfessor));
       List<Marcacao> marcList = professor.getMarcacao();

        List<MarcacaoResponse> list = marcList.stream().map(Marcacao -> mapper.map(Marcacao, MarcacaoResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<MarcacaoResponse> listarTodasMarcacoesPorAluno(Long idAluno) {

        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> idNotFound(idAluno));
        List<Marcacao> marcList = aluno.getMarcacao();

        List<MarcacaoResponse> list = marcList.stream().map(Marcacao -> mapper.map(Marcacao, MarcacaoResponse.class))
                .collect(Collectors.toList());
        return list;
    }

    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }
}
