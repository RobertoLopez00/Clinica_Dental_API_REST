package com.example.clinicadental.services;

import com.example.clinicadental.dto.HorarioDto;
import java.util.List;
import java.util.Optional;

public interface HorarioService {
    List<HorarioDto> getAllHorarios();
    Optional<HorarioDto> getHorarioById(Integer id);
    HorarioDto createHorario(HorarioDto horarioDto);
    Optional<HorarioDto> updateHorario(Integer id, HorarioDto horarioDto);
    void deleteHorario(Integer id);
}