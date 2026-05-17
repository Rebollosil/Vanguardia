package com.auditoria.backend.repository;

import com.auditoria.backend.entity.Hallazgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallazgoRepository extends JpaRepository<Hallazgo, Long> {
}