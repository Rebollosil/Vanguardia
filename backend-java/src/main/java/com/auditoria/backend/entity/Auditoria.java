package com.auditoria.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long idAuditoria;

    // Relación: Muchas auditorías pertenecen a un usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String lenguaje; // ej: "Java", "Python"

    @Lob // Para textos largos
    @Column(name = "codigo_original", nullable = false, columnDefinition = "TEXT")
    private String codigoOriginal;

    @Lob
    @Column(name = "codigo_refactorizado", columnDefinition = "TEXT")
    private String codigoRefactorizado;

    @Lob
    @Column(name = "explicacion_pedagogica", columnDefinition = "TEXT")
    private String explicacionPedagogica;

    @Column(name = "fecha_consulta", nullable = false)
    private LocalDateTime fechaConsulta = LocalDateTime.now();

    // Relación: Una auditoría tiene muchos hallazgos/errores
    @OneToMany(mappedBy = "auditoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hallazgo> hallazgos;
}