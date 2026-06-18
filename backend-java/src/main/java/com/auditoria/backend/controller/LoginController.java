package com.auditoria.backend.controller;

import com.auditoria.backend.entity.Usuario;
import com.auditoria.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- Importación importante
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // <-- Inyectamos el encriptador que definimos en el SecurityConfig

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; 
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        // ENCRIPCION OBLIGATORIA: Tomamos la clave en texto plano y lahaseamos con BCrypt
        String passwordHaseada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordHaseada);
        
        // Ahora sí guardamos de forma segura en MySQL
        usuarioRepository.save(usuario);
        
        return "redirect:/login?registrado=true";
    }
}