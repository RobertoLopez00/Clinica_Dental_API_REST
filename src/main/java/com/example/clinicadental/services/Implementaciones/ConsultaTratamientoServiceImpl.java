package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.ConsultaTratamientoDto;
import com.example.clinicadental.entities.ConsultaTratamiento;
import com.example.clinicadental.repositories.ConsultaTratamientoRepository;
import com.example.clinicadental.services.ConsultaTratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaTratamientoServiceImpl implements ConsultaTratamientoService {

    @Autowired
    private ConsultaTratamientoRepository consultaTratamientoRepository;

    @Override
    public List<ConsultaTratamientoDto> getAllConsultaTratamientos() {
        List<ConsultaTratamiento> consultaTratamientos = consultaTratamientoRepository.findAll();
        List<ConsultaTratamientoDto> consultaTratamientoDtos = new ArrayList<>();
        for (ConsultaTratamiento consultaTratamiento : consultaTratamientos) {
            consultaTratamientoDtos.add(convertToDto(consultaTratamiento));
        }
        return consultaTratamientoDtos;
    }

    @Override
    public Optional<ConsultaTratamientoDto> getConsultaTratamientoById(Integer id) {
        return consultaTratamientoRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public ConsultaTratamientoDto createConsultaTratamiento(ConsultaTratamientoDto consultaTratamientoDto) {
        ConsultaTratamiento consultaTratamiento = convertToEntity(consultaTratamientoDto);
        ConsultaTratamiento savedConsultaTratamiento = consultaTratamientoRepository.save(consultaTratamiento);
        return convertToDto(savedConsultaTratamiento);
    }

    @Override
    public Optional<ConsultaTratamientoDto> updateConsultaTratamiento(Integer id, ConsultaTratamientoDto consultaTratamientoDto) {
        if (consultaTratamientoRepository.existsById(id)) {
            ConsultaTratamiento consultaTratamiento = convertToEntity(consultaTratamientoDto);
            consultaTratamiento.setId(id);
            ConsultaTratamiento updatedConsultaTratamiento = consultaTratamientoRepository.save(consultaTratamiento);
            return Optional.of(convertToDto(updatedConsultaTratamiento));
        }
        return Optional.empty();
    }

    @Override
    public void deleteConsultaTratamiento(Integer id) {
        consultaTratamientoRepository.deleteById(id);
    }

    private ConsultaTratamientoDto convertToDto(ConsultaTratamiento consultaTratamiento) {
        ConsultaTratamientoDto consultaTratamientoDto = new ConsultaTratamientoDto();
        consultaTratamientoDto.setId(consultaTratamiento.getId());
        consultaTratamientoDto.setConsultaId(consultaTratamiento.getConsulta().getId());
        consultaTratamientoDto.setTratamientoId(consultaTratamiento.getTratamiento().getId());
        return consultaTratamientoDto;
    }

    private ConsultaTratamiento convertToEntity(ConsultaTratamientoDto consultaTratamientoDto) {
        ConsultaTratamiento consultaTratamiento = new ConsultaTratamiento();
        consultaTratamiento.setId(consultaTratamientoDto.getId());
        // Set Consulta and Tratamiento entities based on IDs
        // This requires fetching the entities from their respective repositories
        return consultaTratamiento;
    }
}