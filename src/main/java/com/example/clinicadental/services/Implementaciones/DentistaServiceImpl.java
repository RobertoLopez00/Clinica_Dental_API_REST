// src/main/java/com/example/clinicadental/services/Implementaciones/DentistaServiceImpl.java
package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.DentistaDto;
import com.example.clinicadental.dto.EspecialidadDto;
import com.example.clinicadental.entities.Dentista;
import com.example.clinicadental.entities.Especialidad;
import com.example.clinicadental.repositories.DentistaRepository;
import com.example.clinicadental.repositories.EspecialidadRepository;
import com.example.clinicadental.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaServiceImpl implements DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public List<DentistaDto> getAllDentistas() {
        List<Dentista> dentistas = (List<Dentista>) dentistaRepository.findAll();
        List<DentistaDto> dentistaDtos = new ArrayList<>();
        for (Dentista dentista : dentistas) {
            dentistaDtos.add(convertToDto(dentista));
        }
        return dentistaDtos;
    }

    @Override
    public Optional<DentistaDto> getDentistaById(Integer id) {
        return dentistaRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public DentistaDto createDentista(DentistaDto dentistaDto) {
        Dentista dentista = convertToEntity(dentistaDto);
        // Asegurarse de que las especialidades estén gestionadas por el contexto de persistencia
        List<Especialidad> managedEspecialidades = new ArrayList<>();
        for (Especialidad especialidad : dentista.getEspecialidades()) {
            Especialidad managedEspecialidad = especialidadRepository.findById(especialidad.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada"));
            managedEspecialidades.add(managedEspecialidad);
        }
        dentista.setEspecialidades(managedEspecialidades);
        Dentista savedDentista = dentistaRepository.save(dentista);
        return convertToDto(savedDentista);
    }

    @Override
    public Optional<DentistaDto> updateDentista(Integer id, DentistaDto dentistaDto) {
        if (dentistaRepository.existsById(id)) {
            Dentista existingDentista = dentistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Dentista no encontrado"));
            existingDentista.setNombre(dentistaDto.getNombre());
            existingDentista.setApellido(dentistaDto.getApellido());
            existingDentista.setDireccion(dentistaDto.getDireccion());
            existingDentista.setTelefono(dentistaDto.getTelefono());

            // Modificar la colección de horarios directamente
            existingDentista.getHorarios().clear();
            existingDentista.getHorarios().addAll(convertToEntity(dentistaDto).getHorarios());

            Dentista updatedDentista = dentistaRepository.save(existingDentista);
            return Optional.of(convertToDto(updatedDentista));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteDentista(Integer id) {
        Dentista dentista = dentistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Dentista no encontrado"));
        dentista.getHorarios().clear();
        dentistaRepository.save(dentista); // Guardar cambios antes de eliminar
        dentistaRepository.delete(dentista);
    }

    private DentistaDto convertToDto(Dentista dentista) {
        DentistaDto dentistaDto = new DentistaDto();
        dentistaDto.setId(dentista.getId());
        dentistaDto.setNombre(dentista.getNombre());
        dentistaDto.setApellido(dentista.getApellido());
        dentistaDto.setDireccion(dentista.getDireccion());
        dentistaDto.setTelefono(dentista.getTelefono());
        if (dentista.getEspecialidades() != null) {
            List<EspecialidadDto> especialidadDtos = new ArrayList<>();
            for (Especialidad especialidad : dentista.getEspecialidades()) {
                EspecialidadDto especialidadDto = new EspecialidadDto();
                especialidadDto.setId(especialidad.getId());
                especialidadDto.setNombre(especialidad.getNombre());
                // Agregar más campos según sea necesario
                especialidadDtos.add(especialidadDto);
            }
            dentistaDto.setEspecialidades(especialidadDtos);
        }
        return dentistaDto;
    }

    private Dentista convertToEntity(DentistaDto dentistaDto) {
        Dentista dentista = new Dentista();
        // No establecer el id manualmente
        dentista.setNombre(dentistaDto.getNombre());
        dentista.setApellido(dentistaDto.getApellido());
        dentista.setDireccion(dentistaDto.getDireccion());
        dentista.setTelefono(dentistaDto.getTelefono());
        if (dentistaDto.getEspecialidades() != null) {
            List<Especialidad> especialidades = new ArrayList<>();
            for (EspecialidadDto especialidadDto : dentistaDto.getEspecialidades()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setId(especialidadDto.getId());
                especialidad.setNombre(especialidadDto.getNombre());
                // Agregar más campos según sea necesario
                especialidades.add(especialidad);
            }
            dentista.setEspecialidades(especialidades);
        }
        return dentista;
    }
}