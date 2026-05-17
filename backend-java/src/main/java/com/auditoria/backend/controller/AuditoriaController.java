package com.auditoria.backend.controller;

import com.auditoria.backend.dto.AnalisisRequestDTO;
import com.auditoria.backend.dto.AnalisisResponseDTO;
import com.auditoria.backend.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @PostMapping("/analizar")
    public ResponseEntity<AnalisisResponseDTO> analizar(@RequestBody AnalisisRequestDTO request) {
        AnalisisResponseDTO response = auditoriaService.analizarCodigo(request);
        return ResponseEntity.ok(response);
    }
}