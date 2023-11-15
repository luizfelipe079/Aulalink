package com.desafioFinal.DesafioFinal.controllers;

import com.desafioFinal.DesafioFinal.dtos.*;
import com.desafioFinal.DesafioFinal.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }

    @PostMapping("/signup-aluno")
    public JwtAuthenticationResponse signupAluno(@RequestBody AlunoRequest request) {
        return authenticationService.signupAluno(request);
    }

    @PostMapping("/signup-professor")
    public JwtAuthenticationResponse signupProfessor(@RequestBody ProfessorRequest request) {
        return authenticationService.signupProfessor(request);
    }

    // Criar signup aluno e professor, sem autenticação e criar um singup para ADMINS, onde precisará passar o token de autenticação
    // Adicionar a ROLE ao payload do token para que seja possível verificar se é permitida determinada ação para o usuário autenticado

    // Inserção de tags apenas pelo admin
}