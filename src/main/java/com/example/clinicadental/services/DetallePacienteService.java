package com.example.clinicadental.services;

import com.example.clinicadental.dto.DetallePacienteDto;

import java.util.List;
import java.util.Optional;

public interface DetallePacienteService {
    List<DetallePacienteDto> getAllDetalles();
    Optional<DetallePacienteDto> getDetalleById(Integer id);
    DetallePacienteDto createDetalle(DetallePacienteDto detallePacienteDto);
    Optional<DetallePacienteDto> updateDetalle(Integer id, DetallePacienteDto detallePacienteDto);
    void deleteDetalle(Integer id);
}