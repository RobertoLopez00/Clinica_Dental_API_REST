package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.TratamientoDto;
import com.example.clinicadental.entities.Tratamiento;
import com.example.clinicadental.repositories.TratamientoRepository;
import com.example.clinicadental.services.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TratamientoServiceImpl implements TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Override
    public List<TratamientoDto> getAllTratamientos() {
        List<Tratamiento> tratamientos = (List<Tratamiento>) tratamientoRepository.findAll();
        List<TratamientoDto> tratamientoDtos = new ArrayList<>();
        for (Tratamiento tratamiento : tratamientos) {
            tratamientoDtos.add(convertToDto(tratamiento));
        }
        return tratamientoDtos;
    }

    @Override
    public Optional<TratamientoDto> getTratamientoById(Integer id) {
        return tratamientoRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public TratamientoDto createTratamiento(TratamientoDto tratamientoDto) {
        Tratamiento tratamiento = convertToEntity(tratamientoDto);
        Tratamiento savedTratamiento = tratamientoRepository.save(tratamiento);
        return convertToDto(savedTratamiento);
    }

    @Override
    public Optional<TratamientoDto> updateTratamiento(Integer id, TratamientoDto tratamientoDto) {
        if (tratamientoRepository.existsById(id)) {
            Tratamiento tratamiento = convertToEntity(tratamientoDto);
            tratamiento.setId(id);
            Tratamiento updatedTratamiento = tratamientoRepository.save(tratamiento);
            return Optional.of(convertToDto(updatedTratamiento));
        }
        return Optional.empty();
    }

    @Override
    public void deleteTratamiento(Integer id) {
        tratamientoRepository.deleteById(id);
    }

    private TratamientoDto convertToDto(Tratamiento tratamiento) {
        TratamientoDto tratamientoDto = new TratamientoDto();
        tratamientoDto.setId(tratamiento.getId());
        tratamientoDto.setNombre(tratamiento.getNombre());
        tratamientoDto.setDescripcion(tratamiento.getDescripcion());
        // Agregar más campos según sea necesario
        return tratamientoDto;
    }

    private Tratamiento convertToEntity(TratamientoDto tratamientoDto) {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre(tratamientoDto.getNombre());
        tratamiento.setDescripcion(tratamientoDto.getDescripcion());
        // Agregar más campos según sea necesario
        return tratamiento;
    }
}