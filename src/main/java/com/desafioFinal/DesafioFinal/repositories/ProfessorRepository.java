package com.desafioFinal.DesafioFinal.repositories;

import com.desafioFinal.DesafioFinal.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByEmail(String email);

    Optional<Professor> findByNome(@Param("nome") String nome);
}
