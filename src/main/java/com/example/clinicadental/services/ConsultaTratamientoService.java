package com.example.clinicadental.services;

import com.example.clinicadental.dto.ConsultaTratamientoDto;

import java.util.List;
import java.util.Optional;

public interface ConsultaTratamientoService {
    List<ConsultaTratamientoDto> getAllConsultaTratamientos();
    Optional<ConsultaTratamientoDto> getConsultaTratamientoById(Integer id);
    ConsultaTratamientoDto createConsultaTratamiento(ConsultaTratamientoDto consultaTratamientoDto);
    Optional<ConsultaTratamientoDto> updateConsultaTratamiento(Integer id, ConsultaTratamientoDto consultaTratamientoDto);
    void deleteConsultaTratamiento(Integer id);
}
