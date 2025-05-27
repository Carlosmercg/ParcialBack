package com.example.parcialback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parcialback.DTO.entidadDTO;
import com.example.parcialback.services.EntidadService;

@RestController
@RequestMapping("/api/entidades")
public class EntidadController {

    private final EntidadService entidadService;

    public EntidadController(EntidadService entidadService) {
        this.entidadService = entidadService;
    }


    @PostMapping("/create")
    public ResponseEntity<entidadDTO> crearEntidad(@RequestBody entidadDTO entidadDto) {
        entidadDTO creada = entidadService.crearEntidad(entidadDto);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<entidadDTO> actualizarEntidad(@PathVariable Long id, @RequestBody entidadDTO entidadDto) {
        entidadDTO actualizada = entidadService.actualizarEntidad(id, entidadDto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarEntidad(@PathVariable Long id) {
        entidadService.eliminarEntidad(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<entidadDTO>> listarEntidades() {
        List<entidadDTO> entidades = entidadService.listarEntidades();
        return ResponseEntity.ok(entidades);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<entidadDTO> obtenerEntidadPorId(@PathVariable Long id) {
        entidadDTO entidad = entidadService.obtenerEntidadPorId(id);
        return ResponseEntity.ok(entidad);
    }
}

