package com.example.parcialback.repositories;

import com.example.parcialback.entities.entidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadRepository extends JpaRepository<entidad, Long> {
    // Consultas personalizadas opcionales
}
