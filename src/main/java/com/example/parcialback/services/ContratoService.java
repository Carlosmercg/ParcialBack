package com.example.parcialback.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.parcialback.DTO.contratoDTO;
import com.example.parcialback.entities.contrato;
import com.example.parcialback.entities.entidad;
import com.example.parcialback.repositories.ContratoRepository;
import com.example.parcialback.repositories.EntidadRepository;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final EntidadRepository entidadRepository;
    private final ModelMapper modelMapper;

    public ContratoService(ContratoRepository contratoRepository, EntidadRepository entidadRepository, ModelMapper modelMapper) {
        this.contratoRepository = contratoRepository;
        this.entidadRepository = entidadRepository;
        this.modelMapper = modelMapper;
    }

    public contratoDTO crearContrato(contratoDTO dto) {
        entidad entidad = entidadRepository.findById(dto.getEntidadId())
            .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));

        contrato contrato = modelMapper.map(dto, contrato.class);
        contrato.setEntidad(entidad);

        contrato guardado = contratoRepository.save(contrato);
        // Para devolver el DTO con solo el id de la entidad
        contratoDTO resultado = modelMapper.map(guardado, contratoDTO.class);
        resultado.setEntidadId(guardado.getEntidad().getId());
        return resultado;
    }

    public contratoDTO actualizarContrato(Long id, contratoDTO dto) {
        contrato existente = contratoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contrato no encontrado"));

        entidad entidad = entidadRepository.findById(dto.getEntidadId())
            .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));

        // Actualiza campos manualmente para evitar sobreescribir la entidad con el dto
        existente.setIdentificador(dto.getIdentificador());
        existente.setValor(dto.getValor());
        existente.setNombreContratante(dto.getNombreContratante());
        existente.setDocumentoContratante(dto.getDocumentoContratante());
        existente.setNombreContratista(dto.getNombreContratista());
        existente.setDocumentoContratista(dto.getDocumentoContratista());
        existente.setFechaInicial(dto.getFechaInicial());
        existente.setFechaFinal(dto.getFechaFinal());
        existente.setEntidad(entidad);

        contrato actualizado = contratoRepository.save(existente);

        contratoDTO resultado = modelMapper.map(actualizado, contratoDTO.class);
        resultado.setEntidadId(actualizado.getEntidad().getId());
        return resultado;
    }

    public void eliminarContrato(Long id) {
        contratoRepository.deleteById(id);
    }

    public List<contratoDTO> listarContratos() {
        return contratoRepository.findAll().stream()
                .map(c -> {
                    contratoDTO dto = modelMapper.map(c, contratoDTO.class);
                    dto.setEntidadId(c.getEntidad().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public contratoDTO obtenerContratoPorId(Long id) {
        contrato c = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado"));

        contratoDTO dto = modelMapper.map(c, contratoDTO.class);
        dto.setEntidadId(c.getEntidad().getId());
        return dto;
    }
}
