package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.EspecialidadDto;
import com.example.clinicadental.entities.Especialidad;
import com.example.clinicadental.repositories.EspecialidadRepository;
import com.example.clinicadental.services.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public List<EspecialidadDto> getAllEspecialidades() {
        List<Especialidad> especialidades = (List<Especialidad>) especialidadRepository.findAll();
        List<EspecialidadDto> especialidadDtos = new ArrayList<>();
        for (Especialidad especialidad : especialidades) {
            especialidadDtos.add(convertToDto(especialidad));
        }
        return especialidadDtos;
    }

    @Override
    public Optional<EspecialidadDto> getEspecialidadById(Integer id) {
        return especialidadRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public EspecialidadDto createEspecialidad(EspecialidadDto especialidadDto) {
        Especialidad especialidad = convertToEntity(especialidadDto);
        Especialidad savedEspecialidad = especialidadRepository.save(especialidad);
        return convertToDto(savedEspecialidad);
    }

    @Override
    public Optional<EspecialidadDto> updateEspecialidad(Integer id, EspecialidadDto especialidadDto) {
        if (especialidadRepository.existsById(id)) {
            Especialidad especialidad = convertToEntity(especialidadDto);
            especialidad.setId(id);
            Especialidad updatedEspecialidad = especialidadRepository.save(especialidad);
            return Optional.of(convertToDto(updatedEspecialidad));
        }
        return Optional.empty();
    }

    @Override
    public void deleteEspecialidad(Integer id) {
        especialidadRepository.deleteById(id);
    }

    private EspecialidadDto convertToDto(Especialidad especialidad) {
        EspecialidadDto especialidadDto = new EspecialidadDto();
        especialidadDto.setId(especialidad.getId());
        especialidadDto.setNombre(especialidad.getNombre());
        // Agregar más campos según sea necesario
        return especialidadDto;
    }

    private Especialidad convertToEntity(EspecialidadDto especialidadDto) {
        Especialidad especialidad = new Especialidad();
        especialidad.setId(especialidadDto.getId());
        especialidad.setNombre(especialidadDto.getNombre());
        // Agregar más campos según sea necesario
        return especialidad;
    }
}