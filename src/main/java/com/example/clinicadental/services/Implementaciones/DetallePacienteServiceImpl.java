package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.DetallePacienteDto;
import com.example.clinicadental.dto.PacienteDto;
import com.example.clinicadental.entities.DetallePaciente;
import com.example.clinicadental.entities.Paciente;
import com.example.clinicadental.repositories.DetallePacienteRepository;
import com.example.clinicadental.services.DetallePacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetallePacienteServiceImpl implements DetallePacienteService {

    @Autowired
    private DetallePacienteRepository detallePacienteRepository;

    @Override
    public List<DetallePacienteDto> getAllDetalles() {
        List<DetallePaciente> detalles = (List<DetallePaciente>) detallePacienteRepository.findAll();
        List<DetallePacienteDto> detalleDtos = new ArrayList<>();
        for (DetallePaciente detalle : detalles) {
            detalleDtos.add(convertToDto(detalle));
        }
        return detalleDtos;
    }

    @Override
    public Optional<DetallePacienteDto> getDetalleById(Integer id) {
        return detallePacienteRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public DetallePacienteDto createDetalle(DetallePacienteDto detallePacienteDto) {
        DetallePaciente detalle = convertToEntity(detallePacienteDto);
        DetallePaciente savedDetalle = detallePacienteRepository.save(detalle);
        return convertToDto(savedDetalle);
    }

    @Override
    public Optional<DetallePacienteDto> updateDetalle(Integer id, DetallePacienteDto detallePacienteDto) {
        if (detallePacienteRepository.existsById(id)) {
            DetallePaciente detalle = convertToEntity(detallePacienteDto);
            detalle.setId(id);
            DetallePaciente updatedDetalle = detallePacienteRepository.save(detalle);
            return Optional.of(convertToDto(updatedDetalle));
        }
        return Optional.empty();
    }

    @Override
    public void deleteDetalle(Integer id) {
        detallePacienteRepository.deleteById(id);
    }

    private DetallePacienteDto convertToDto(DetallePaciente detalle) {
        DetallePacienteDto detalleDto = new DetallePacienteDto();
        detalleDto.setId(detalle.getId());
        detalleDto.setDetalle(detalle.getDetalle());
        if (detalle.getPaciente() != null) {
            PacienteDto pacienteDto = new PacienteDto();
            pacienteDto.setId(detalle.getPaciente().getId());
            pacienteDto.setNombre(detalle.getPaciente().getNombre());
            pacienteDto.setApellido(detalle.getPaciente().getApellido());
            pacienteDto.setDireccion(detalle.getPaciente().getDireccion());
            pacienteDto.setTelefono(detalle.getPaciente().getTelefono());
            pacienteDto.setFechaNacimiento(detalle.getPaciente().getFechaNacimiento());
            // Agregar más campos según sea necesario
            detalleDto.setPaciente(pacienteDto);
        }
        return detalleDto;
    }

    private DetallePaciente convertToEntity(DetallePacienteDto detalleDto) {
        DetallePaciente detalle = new DetallePaciente();
        detalle.setId(detalleDto.getId());
        detalle.setDetalle(detalleDto.getDetalle());
        if (detalleDto.getPaciente() != null) {
            Paciente paciente = new Paciente();
            paciente.setId(detalleDto.getPaciente().getId());
            paciente.setNombre(detalleDto.getPaciente().getNombre());
            paciente.setApellido(detalleDto.getPaciente().getApellido());
            paciente.setDireccion(detalleDto.getPaciente().getDireccion());
            paciente.setTelefono(detalleDto.getPaciente().getTelefono());
            paciente.setFechaNacimiento(detalleDto.getPaciente().getFechaNacimiento());
            // Agregar más campos según sea necesario
            detalle.setPaciente(paciente);
        }
        return detalle;
    }
}