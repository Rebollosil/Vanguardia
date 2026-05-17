package com.auditoria.backend.repository;

import com.auditoria.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Con solo nombrar el método así, Spring Data crea automáticamente 
    // la consulta SQL por detrás para verificar si un email ya existe.
    boolean existsByEmail(String email);
}