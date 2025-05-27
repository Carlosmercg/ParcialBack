package com.example.parcialback.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class entidadDTO {

    private Long id;
    private String nit;
    private String nombre;
    private List<Long> contratosIds;  // solo lista con IDs de contratos

}
