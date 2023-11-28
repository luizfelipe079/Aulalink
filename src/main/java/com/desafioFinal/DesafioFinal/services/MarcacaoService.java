package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.*;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.Aluno;
import com.desafioFinal.DesafioFinal.models.HorariosDisponiveis;
import com.desafioFinal.DesafioFinal.models.Marcacao;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.repositories.AlunoRepository;
import com.desafioFinal.DesafioFinal.repositories.MarcacaoRepository;
import com.desafioFinal.DesafioFinal.repositories.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private ProfessorResponse professorSalvoResponse;

    public MarcacaoResponse criarMarcacao(MarcacaoRequest request) {

        Marcacao marc = new Marcacao();

        BeanUtils.copyProperties(request, marc);

        return mapper.map(marcacaoRepository.save(marc), MarcacaoResponse.class);


    }

    public MarcacaoResponse vincularMarcacaoAoProfessor(MarcacaoVinculaProfessorRequest request) {

        Marcacao marca = marcacaoRepository.findById(request.getId_marcacao()).orElseThrow(() -> idNotFound(request.getId_marcacao()));
        Professor prof = professorRepository.findById(request.getId_professor()).orElseThrow(() -> idNotFound(request.getId_professor()));

        marca.setProfessor(prof);

        return mapper.map(marcacaoRepository.save(marca), MarcacaoResponse.class);

    }

    public MarcacaoResponse vincularMarcacaoAoAluno(MarcacaoVinculaAlunoRequest request) {

        Marcacao marca = marcacaoRepository.findById(request.getId_marcacao()).orElseThrow(() -> idNotFound(request.getId_marcacao()));
        Aluno aluno = alunoRepository.findById(request.getId_aluno()).orElseThrow(() -> idNotFound(request.getId_aluno()));

        marca.setAluno(aluno);
//        aluno.getMarcacao().add(marca);

//        alunoRepository.save(aluno);

        return mapper.map(marcacaoRepository.save(marca), MarcacaoResponse.class);

    }

    public List<MarcacaoResponse> criarMultiplasMarcacoesDisponiveis(ListMarcacoesDisponiveisRequest request) {
        List<MarcacaoResponse> marcacaoDisponiveisResponses = new ArrayList<>();

        for (Marcacao marcacoesDisponiveis : request.getMarcacoes()) {
            Professor professor = professorRepository.findById(request.getIdProfessor()).orElseThrow(() -> idNotFound(request.getIdProfessor()));

            Marcacao save = marcacaoRepository.save(new Marcacao(null, marcacoesDisponiveis.getDescricao(), marcacoesDisponiveis.getDataInicio(),
                    marcacoesDisponiveis.getDataTermino(), false, professor, null));
            ProfessorResponse professorSalvoResponse = mapper.map(save.getProfessor(), ProfessorResponse.class);

            marcacaoDisponiveisResponses.add(new MarcacaoResponse(save.getId(), save.getDescricao(), save.getDataInicio(),
                   save.getDataTermino(), save.isHorarioPreenchido(), save.getProfessor(), save.getAluno()));
        }

        return marcacaoDisponiveisResponses;
    }

    public MarcacaoResponse atualizarMarcacao(Long id, MarcacaoRequest request) {

        Marcacao marc = marcacaoRepository.findById(id).orElseThrow(() -> idNotFound(id));
        BeanUtils.copyProperties(request, marc, "id", "professor", "aluno");
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

    public void excluirMarcacao(IdRequest request) {

        Marcacao marc = marcacaoRepository.findById(request.getId()).orElseThrow(() -> idNotFound(request.getId()));
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
