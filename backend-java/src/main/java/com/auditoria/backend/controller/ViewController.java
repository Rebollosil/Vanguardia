package com.auditoria.backend.controller;

import com.auditoria.backend.dto.AnalisisRequestDTO;
import com.auditoria.backend.dto.AnalisisResponseDTO;
import com.auditoria.backend.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @Autowired
    private AuditoriaService auditoriaService;

    // Carga la pantalla inicial vacía al entrar al navegador
    @GetMapping("/")
    public String mostrarPantallaPrincipal(Model model) {
        model.addAttribute("requestDTO", new AnalisisRequestDTO());
        model.addAttribute("resultado", null);
        return "index"; // mapea con templates/index.html
    }

    // Procesa el formulario al darle clic al botón "Iniciar Auditoría"
    @PostMapping("/")
    public String procesarAuditoriasWeb(@ModelAttribute("requestDTO") AnalisisRequestDTO request, Model model) {
        // Ejecutamos la lógica que viaja a Python y luego a Gemini
        AnalisisResponseDTO respuestaReal = auditoriaService.analizarCodigo(request);
        
        // Le devolvemos los resultados a la pantalla Thymeleaf
        model.addAttribute("requestDTO", request);
        model.addAttribute("resultado", respuestaReal);
        return "index";
    }
}