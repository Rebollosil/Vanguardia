package com.auditoria.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class AnalisisResponseDTO {
    private String codigoRefactorizado;
    private String explicacionPedagogica;
    private List<HallazgoDTO> hallazgos;
}