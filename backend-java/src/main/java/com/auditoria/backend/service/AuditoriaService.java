package com.auditoria.backend.service;

import com.auditoria.backend.dto.AnalisisRequestDTO;
import com.auditoria.backend.dto.AnalisisResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuditoriaService {

    // La URL exacta donde está escuchando tu servidor de Python
    private final String PYTHON_URL = "http://localhost:8000/api/ia/auditar";
    private final RestTemplate restTemplate = new RestTemplate();

    public AnalisisResponseDTO analizarCodigo(AnalisisRequestDTO request) {
        System.out.println("Java-Orquestador: Reenviando código a Python en puerto 8000...");
        
        try {
            // Hacemos la petición POST a Python enviando los datos y esperando el formato de respuesta
            AnalisisResponseDTO response = restTemplate.postForObject(PYTHON_URL, request, AnalisisResponseDTO.class);
            
            System.out.println("Java-Orquestador: ¡Respuesta recibida con éxito desde Python!");
            return response;
            
        } catch (Exception e) {
            System.err.println("Error al comunicarse con el microservicio de Python: " + e.getMessage());
            // Si Python falla o está apagado, devolvemos un objeto vacío para que no explote el sistema
            return new AnalisisResponseDTO();
        }
    }
}