package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.UsuarioResponse;
import com.desafioFinal.DesafioFinal.models.Usuario;
import com.desafioFinal.DesafioFinal.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ModelMapper mapper;

    public final UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            }
        };
    }

    public UsuarioResponse buscarUsuarioPorEmail(String email) {

        Optional<Usuario> usuario = repository.findByEmail(email);
        Usuario user = usuario.get();
        return mapper.map(user, UsuarioResponse.class);

    }

    public Usuario save(Usuario newUser) {
        if (newUser.getId() == null) {
            newUser.setCreatedAt(LocalDateTime.now());
        }
        newUser.setUpdatedAt(LocalDateTime.now());
        return repository.save(newUser);
    }

}
