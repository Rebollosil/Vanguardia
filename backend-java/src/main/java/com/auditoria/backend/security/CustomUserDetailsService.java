package com.auditoria.backend.security;

import com.auditoria.backend.entity.Usuario;
import com.auditoria.backend.repository.UsuarioRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(
            UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario =
                usuarioRepository.findByUsername(username);

        if (usuario == null) {

            throw new UsernameNotFoundException(
                    "Usuario no encontrado"
            );
        }

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
                )
        );
    }
}