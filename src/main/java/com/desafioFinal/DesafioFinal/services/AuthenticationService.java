package com.desafioFinal.DesafioFinal.services;

import com.desafioFinal.DesafioFinal.dtos.*;
import com.desafioFinal.DesafioFinal.exceptions.AutenticacaoException;
import com.desafioFinal.DesafioFinal.models.Aluno;
import com.desafioFinal.DesafioFinal.models.Professor;
import com.desafioFinal.DesafioFinal.models.Usuario;
import com.desafioFinal.DesafioFinal.models.enums.Role;
import com.desafioFinal.DesafioFinal.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UsuarioRepository userRepository;
    private final UsuarioService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = Usuario
                .builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .role(Role.ROLE_USER)
                .build();

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signin(SignInRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (Exception e) {
            throw new AutenticacaoException();
        }
    }


    public JwtAuthenticationResponse signupAluno(AlunoRequest request) {

        var user = Aluno
                .builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .idade(request.getIdade())
                .cpf(request.getCpf())
                .role(Role.ROLE_ALUNO)
                .build();

        Aluno aluno = new Aluno();

        BeanUtils.copyProperties(user, aluno);
        aluno.setEducacao(request.getEducacao());

        user = userService.save(aluno);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signupProfessor(ProfessorRequest request) {

        var user = Professor
                .builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .idade(request.getIdade())
                .cpf(request.getCpf())
                .role(Role.ROLE_PROFESSOR)
                .build();

        Professor professor = new Professor();

        BeanUtils.copyProperties(user, professor);
        professor.setFormacao(request.getFormacao());

        user = userService.save(professor);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
