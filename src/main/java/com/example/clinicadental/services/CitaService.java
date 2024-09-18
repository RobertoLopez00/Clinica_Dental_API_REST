package com.example.clinicadental.services;

import com.example.clinicadental.dto.CitaDto;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    CitaDto createCita(CitaDto citaDto);
    Optional<CitaDto> getCitaById(Integer id);
    List<CitaDto> getAllActiveCitas();
    Optional<CitaDto> updateCita(Integer id, CitaDto citaDto);
    void deleteCita(Integer id);
}