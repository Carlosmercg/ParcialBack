package com.example.parcialback.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.parcialback.DTO.entidadDTO;
import com.example.parcialback.entities.entidad;
import com.example.parcialback.repositories.EntidadRepository;

@Service
public class EntidadService {

    private final EntidadRepository entidadRepository;
    private final ModelMapper modelMapper;

    public EntidadService(EntidadRepository entidadRepository, ModelMapper modelMapper) {
        this.entidadRepository = entidadRepository;
        this.modelMapper = modelMapper;
    }

    public entidadDTO crearEntidad(entidadDTO dto) {
        entidad entidad = modelMapper.map(dto, entidad.class);
        entidad guardada = entidadRepository.save(entidad);
        return modelMapper.map(guardada, entidadDTO.class);
    }

    public entidadDTO actualizarEntidad(Long id, entidadDTO dto) {
        entidad existente = entidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));

        existente.setNit(dto.getNit());
        existente.setNombre(dto.getNombre());
        // Si tienes contratos, puedes actualizar aquí o manejar aparte

        entidad actualizada = entidadRepository.save(existente);
        return modelMapper.map(actualizada, entidadDTO.class);
    }

    public void eliminarEntidad(Long id) {
        entidadRepository.deleteById(id);
    }

    public List<entidadDTO> listarEntidades() {
        return entidadRepository.findAll().stream()
                .map(e -> modelMapper.map(e, entidadDTO.class))
                .collect(Collectors.toList());
    }

    public entidadDTO obtenerEntidadPorId(Long id) {
        entidad e = entidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));
        return modelMapper.map(e, entidadDTO.class);
    }
}