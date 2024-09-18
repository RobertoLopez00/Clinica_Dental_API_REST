package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.PacienteDto;
import com.example.clinicadental.entities.Paciente;
import com.example.clinicadental.repositories.PacienteRepository;
import com.example.clinicadental.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<PacienteDto> getAllPacientes() {
        return ((List<Paciente>) pacienteRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PacienteDto> getPacienteById(Long id) {
        return pacienteRepository.findById(id.intValue()).map(this::convertToDto);
    }

    @Override
    public PacienteDto createPaciente(PacienteDto pacienteDto) {
        Paciente paciente = convertToEntity(pacienteDto);
        Paciente savedPaciente = pacienteRepository.save(paciente);
        return convertToDto(savedPaciente);
    }

    @Override
    public Optional<PacienteDto> updatePaciente(Long id, PacienteDto pacienteDto) {
        if (pacienteRepository.existsById(id.intValue())) {
            Paciente paciente = convertToEntity(pacienteDto);
            paciente.setId(id.intValue());
            Paciente updatedPaciente = pacienteRepository.save(paciente);
            return Optional.of(convertToDto(updatedPaciente));
        }
        return Optional.empty();
    }

    @Override
    public void deletePaciente(Integer id) {
        pacienteRepository.deleteById(id.intValue());
    }

    private PacienteDto convertToDto(Paciente paciente) {
        return new PacienteDto(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getTelefono(),
                paciente.getDireccion(),
                paciente.getFechaNacimiento()
        );
    }

    private Paciente convertToEntity(PacienteDto pacienteDto) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDto.getId() != null ? pacienteDto.getId().intValue() : null);
        paciente.setNombre(pacienteDto.getNombre());
        paciente.setApellido(pacienteDto.getApellido());
        paciente.setTelefono(pacienteDto.getTelefono());
        paciente.setDireccion(pacienteDto.getDireccion());
        paciente.setFechaNacimiento(pacienteDto.getFechaNacimiento());
        return paciente;
    }
}