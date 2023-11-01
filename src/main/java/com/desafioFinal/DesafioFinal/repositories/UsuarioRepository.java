package com.desafioFinal.DesafioFinal.repositories;

import com.desafioFinal.DesafioFinal.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
