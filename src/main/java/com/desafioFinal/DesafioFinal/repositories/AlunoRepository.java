package com.desafioFinal.DesafioFinal.repositories;

import com.desafioFinal.DesafioFinal.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByEmail(String email);

}
