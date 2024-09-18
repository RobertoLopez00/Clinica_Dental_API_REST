package com.example.clinicadental.services;

import com.example.clinicadental.dto.EspecialidadDto;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    List<EspecialidadDto> getAllEspecialidades();
    Optional<EspecialidadDto> getEspecialidadById(Integer id);
    EspecialidadDto createEspecialidad(EspecialidadDto especialidadDto);
    Optional<EspecialidadDto> updateEspecialidad(Integer id, EspecialidadDto especialidadDto);
    void deleteEspecialidad(Integer id);
}