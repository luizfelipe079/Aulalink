package com.desafioFinal.DesafioFinal.repositories;

import com.desafioFinal.DesafioFinal.models.HorariosDisponiveis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorariosDisponiveisRepository extends JpaRepository<HorariosDisponiveis, Long> {

//    @Query("SELECT h FROM HorarioDisponiveis h WHERE h.professor.id = :idProfessor")
    List<HorariosDisponiveis> findByProfessorId(@Param("idProfessor") Long idProfessor);
}
