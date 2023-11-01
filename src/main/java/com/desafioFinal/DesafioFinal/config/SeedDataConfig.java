package com.desafioFinal.DesafioFinal.config;

import com.desafioFinal.DesafioFinal.models.Usuario;
import com.desafioFinal.DesafioFinal.models.enums.Role;
import com.desafioFinal.DesafioFinal.repositories.UsuarioRepository;
import com.desafioFinal.DesafioFinal.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            Usuario admin = Usuario
                    .builder()
                    .nome("admin")
                    .sobrenome("admin")
                    .email("admin@admin.com")
                    .senha(passwordEncoder.encode("password"))
                    .role(Role.ROLE_ADMIN)
                    .build();

            userService.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }

}
