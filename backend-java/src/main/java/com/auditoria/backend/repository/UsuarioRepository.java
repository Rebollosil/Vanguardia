package com.auditoria.backend.repository;

import com.auditoria.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Cambiamos "Email" por "Username" para que coincida con nuestra clase Usuario
    Usuario findByUsername(String username);
    
    boolean existsByUsername(String username);
}