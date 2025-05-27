package com.example.parcialback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parcialback.entities.contrato;

@Repository
public interface ContratoRepository extends JpaRepository<contrato, Long> {
    
}