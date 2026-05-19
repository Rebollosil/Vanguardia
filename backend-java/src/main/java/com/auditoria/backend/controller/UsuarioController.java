package com.auditoria.backend.controller;

import com.auditoria.backend.entity.Usuario;
import com.auditoria.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Esto permite que el Frontend se conecte sin problemas de CORS
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. ENDPOINT PARA REGISTRAR UN USUARIO
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        // Validamos si el nombre de usuario ya existe en XAMPP
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("error", "El nombre de usuario ya está registrado."));
        }

        // Guardamos el usuario en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        
        // Respondemos un mensaje de éxito
        return ResponseEntity.ok(Map.of(
            "mensaje", "Usuario registrado con éxito.",
            "id", usuarioGuardado.getId(),
            "username", usuarioGuardado.getUsername()
        ));
    }

    // 2. ENDPOINT PARA INICIAR SESIÓN (LOGIN)
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario) {
        // Buscamos el usuario por su username
        Usuario usuarioEncontrado = usuarioRepository.findByUsername(usuario.getUsername());

        // Validamos que exista y que la contraseña coincida
        if (usuarioEncontrado == null || !usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas."));
        }

        // Login exitoso
        return ResponseEntity.ok(Map.of(
            "mensaje", "Inicio de sesión exitoso.",
            "username", usuarioEncontrado.getUsername()
        ));
    }
}