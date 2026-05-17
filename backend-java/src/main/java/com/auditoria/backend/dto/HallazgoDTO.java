package com.auditoria.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HallazgoDTO {
    private String categoria;
    private String severidad;
    private Integer lineaCodigo;
    private String descripcion;
}