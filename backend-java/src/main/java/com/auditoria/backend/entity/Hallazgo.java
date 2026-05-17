package com.auditoria.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hallazgos")
public class Hallazgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hallazgo")
    private Long idHallazgo;

    // Relación: Muchos hallazgos pertenecen a una auditoría
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_auditoria", nullable = false)
    private Auditoria auditoria;

    @Column(nullable = false, length = 50)
    private String categoria; // "Seguridad", "Sintaxis", "Buenas Prácticas"

    @Column(nullable = false, length = 50)
    private String severidad; // "Crítico", "Advertencia", "Sugerencia"

    @Column(name = "linea_codigo")
    private Integer lineaCodigo; // Integer en lugar de int por si es nulo

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;
}