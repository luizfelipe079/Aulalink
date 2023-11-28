package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.IdRequest;
import com.desafioFinal.DesafioFinal.dtos.ProfessorRequest;
import com.desafioFinal.DesafioFinal.dtos.ProfessorResponse;
import com.desafioFinal.DesafioFinal.dtos.TagsResponse;
import com.desafioFinal.DesafioFinal.exceptions.ResourceNotFoundException;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.models.Tags;
import com.desafioFinal.DesafioFinal.repositories.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {


    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private TagsService tagsService;

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

   public ProfessorResponse listarTodasTagsDoProfessor(String nomeProfessor){

        Optional<Professor> professor = professorRepository.findByNome(nomeProfessor);

        ProfessorResponse prof = mapper.map(professor.get(), ProfessorResponse.class);

        return prof;

   }

    public List<ProfessorResponse> listarTodosProfessoresPorTags(String tag) {

        if (tag == null || tag.isEmpty()) {
            throw new RuntimeException("A lista de tags não pode estar vazia");
        }
        List<Professor> lista = professorRepository.findAll();

        List<ProfessorResponse> professoresComTags = lista.stream()
                .filter(Professor -> containsTag(Professor, tag))
                .map(Professor -> mapper.map(Professor, ProfessorResponse.class)) // Supondo que você tenha uma classe ProfessorResponse
                .collect(Collectors.toList());

        if (professoresComTags.isEmpty()) {
            throw new ResourceNotFoundException("Não existe professor com estas tags");
        }

        return professoresComTags;
    }


//Falta implementar este metodo com o front
//    public List<ProfessorResponse> listarTodosProfessoresPorTags(String[] tags) {
//
//            if (Arrays.stream(tags).toList().isEmpty()) {
//                throw new RuntimeException("A lista de tags não pode estar vazia");
//            }
//
//            List<Professor> lista = professorRepository.findAll();
//
//            List<ProfessorResponse> professoresComTags = lista.stream()
//                    .filter(professor -> containsAnyTag(professor, tags))
//                    .map(ProfessorResponse::new) // Supondo que você tenha uma classe ProfessorResponse
//                    .collect(Collectors.toList());
//
//            if (professoresComTags.isEmpty()) {
//                throw new RuntimeException("Não existe professor com estas tags");
//            }
//
//            return professoresComTags;
//
//    }


    public void excluirProfessor(IdRequest request) {

        Professor professor = professorRepository.findById(request.getId()).orElseThrow(() -> idNotFound(request.getId()));
        professorRepository.delete(professor);

    }

    private boolean containsTag(Professor professor, String tag) {
        return professor.getTag().stream().anyMatch(tags -> tags.getDescricao().equals(tag));
    }

//    private boolean containsAnyTag(Professor professor, String[] tags) {
//        List<List<Tags>> professorTags = Arrays.asList(professor.getTag()); // Substitua pelo método adequado de obter as tags do professor
//        return professorTags.containsAll(Arrays.asList(tags));
//    }

    public RuntimeException idNotFound(Long id) {
        throw new ResourceNotFoundException("Id not found " + id);
    }


}
