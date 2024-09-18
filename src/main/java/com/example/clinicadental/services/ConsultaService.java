package com.example.clinicadental.services;

import com.example.clinicadental.dto.ConsultaDto;
import com.example.clinicadental.entities.Consulta;
import com.example.clinicadental.entities.Tratamiento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {
    List<ConsultaDto> getAllConsultas();
    Optional<ConsultaDto> getConsultaById(Integer id);
    ConsultaDto createConsulta(ConsultaDto consultaDto);
    Optional<ConsultaDto> updateConsulta(Integer id, ConsultaDto consultaDto);
    void deleteConsulta(Integer id);

}