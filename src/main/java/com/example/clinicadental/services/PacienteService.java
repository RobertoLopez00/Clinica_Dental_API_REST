package com.example.clinicadental.services;

import com.example.clinicadental.dto.PacienteDto;
import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<PacienteDto> getAllPacientes();

    Optional<PacienteDto> getPacienteById(Long id);

    PacienteDto createPaciente(PacienteDto pacienteDto);

    Optional<PacienteDto> updatePaciente(Long id, PacienteDto pacienteDto);

    void deletePaciente(Integer id);
}