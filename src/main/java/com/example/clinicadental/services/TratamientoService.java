package com.example.clinicadental.services;

import com.example.clinicadental.dto.TratamientoDto;

import java.util.List;
import java.util.Optional;

public interface TratamientoService {
    List<TratamientoDto> getAllTratamientos();
    Optional<TratamientoDto> getTratamientoById(Integer id);
    TratamientoDto createTratamiento(TratamientoDto tratamientoDto);
    Optional<TratamientoDto> updateTratamiento(Integer id, TratamientoDto tratamientoDto);
    void deleteTratamiento(Integer id);
}