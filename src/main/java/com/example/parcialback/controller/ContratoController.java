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

import com.example.parcialback.DTO.contratoDTO;
import com.example.parcialback.services.ContratoService;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @PostMapping("/create")
    public ResponseEntity<contratoDTO> crearContrato(@RequestBody contratoDTO contratoDto) {
        contratoDTO creado = contratoService.crearContrato(contratoDto);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<contratoDTO> actualizarContrato(@PathVariable Long id, @RequestBody contratoDTO contratoDto) {
        contratoDTO actualizado = contratoService.actualizarContrato(id, contratoDto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long id) {
        contratoService.eliminarContrato(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<contratoDTO>> listarContratos() {
        List<contratoDTO> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<contratoDTO> obtenerContratoPorId(@PathVariable Long id) {
        contratoDTO contrato = contratoService.obtenerContratoPorId(id);
        return ResponseEntity.ok(contrato);
    }
}
