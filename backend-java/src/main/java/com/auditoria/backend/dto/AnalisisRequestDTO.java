package com.auditoria.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnalisisRequestDTO {
    private String lenguaje;
    private String codigoFuente;
}