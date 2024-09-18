package com.example.clinicadental.services;

import com.example.clinicadental.dto.DentistaDto;

import java.util.List;
import java.util.Optional;

public interface DentistaService {
    List<DentistaDto> getAllDentistas();
    Optional<DentistaDto> getDentistaById(Integer id);
    DentistaDto createDentista(DentistaDto dentistaDto);
    Optional<DentistaDto> updateDentista(Integer id, DentistaDto dentistaDto);
    void deleteDentista(Integer id);
}