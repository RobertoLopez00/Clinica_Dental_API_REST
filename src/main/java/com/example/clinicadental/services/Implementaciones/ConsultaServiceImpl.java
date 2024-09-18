package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.ConsultaDto;
import com.example.clinicadental.dto.PacienteDto;
import com.example.clinicadental.dto.TratamientoDto;
import com.example.clinicadental.entities.Consulta;
import com.example.clinicadental.entities.Paciente;
import com.example.clinicadental.entities.Tratamiento;
import com.example.clinicadental.repositories.ConsultaRepository;
import com.example.clinicadental.repositories.ConsultaTratamientoRepository;
import com.example.clinicadental.repositories.TratamientoRepository;
import com.example.clinicadental.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaTratamientoRepository consultaTratamientoRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;


    public ConsultaServiceImpl(ConsultaRepository consultaRepository, ConsultaTratamientoRepository consultaTratamientoRepository) {
        this.consultaRepository = consultaRepository;
        this.consultaTratamientoRepository = consultaTratamientoRepository;
    }

    @Override
    public List<ConsultaDto> getAllConsultas() {
        List<Consulta> consultas = (List<Consulta>) consultaRepository.findAll();
        List<ConsultaDto> consultaDtos = new ArrayList<>();
        for (Consulta consulta : consultas) {
            consultaDtos.add(convertToDto(consulta));
        }
        return consultaDtos;
    }

    @Override
    public Optional<ConsultaDto> getConsultaById(Integer id) {
        return consultaRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public ConsultaDto createConsulta(ConsultaDto consultaDto) {
        Consulta consulta = convertToEntity(consultaDto);
        consulta.setId(null); // Asegúrate de que el ID no esté establecido

        // Asegurarse de que los tratamientos estén gestionados por el contexto de persistencia
        List<Tratamiento> managedTratamientos = new ArrayList<>();
        for (Tratamiento tratamiento : consulta.getTratamientos()) {
            Tratamiento managedTratamiento = tratamientoRepository.findById(tratamiento.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Tratamiento no encontrado"));
            managedTratamientos.add(managedTratamiento);
        }
        consulta.setTratamientos(managedTratamientos);

        Consulta savedConsulta = consultaRepository.save(consulta);
        return convertToDto(savedConsulta);
    }
    @Override
    public Optional<ConsultaDto> updateConsulta(Integer id, ConsultaDto consultaDto) {
        if (consultaRepository.existsById(id)) {
            Consulta consulta = convertToEntity(consultaDto);
            consulta.setId(id);
            Consulta updatedConsulta = consultaRepository.save(consulta);
            return Optional.of(convertToDto(updatedConsulta));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteConsulta(Integer id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new RuntimeException("Consulta no encontrada"));
        consulta.getTratamientos().clear();
        consultaRepository.save(consulta); // Guardar cambios antes de eliminar
        consultaRepository.delete(consulta);
    }

    private ConsultaDto convertToDto(Consulta consulta) {
        ConsultaDto consultaDto = new ConsultaDto();
        consultaDto.setId(consulta.getId());
        consultaDto.setFechaHora(consulta.getFechaHora());
        consultaDto.setDiagnostico(consulta.getDiagnostico());
        if (consulta.getPaciente() != null) {
            PacienteDto pacienteDto = new PacienteDto();
            pacienteDto.setId(consulta.getPaciente().getId());
            pacienteDto.setNombre(consulta.getPaciente().getNombre());
            pacienteDto.setApellido(consulta.getPaciente().getApellido());
            pacienteDto.setTelefono(consulta.getPaciente().getTelefono());
            pacienteDto.setDireccion(consulta.getPaciente().getDireccion());
            pacienteDto.setFechaNacimiento(consulta.getPaciente().getFechaNacimiento());
            consultaDto.setPaciente(pacienteDto);
        }
        if (consulta.getTratamientos() != null) {
            List<TratamientoDto> tratamientoDtos = new ArrayList<>();
            for (Tratamiento tratamiento : consulta.getTratamientos()) {
                TratamientoDto tratamientoDto = new TratamientoDto();
                tratamientoDto.setId(tratamiento.getId());
                tratamientoDto.setNombre(tratamiento.getNombre());
                tratamientoDto.setDescripcion(tratamiento.getDescripcion());
                // Agregar más campos según sea necesario
                tratamientoDtos.add(tratamientoDto);
            }
            consultaDto.setTratamientos(tratamientoDtos);
        }
        return consultaDto;
    }

    private Consulta convertToEntity(ConsultaDto consultaDto) {
        Consulta consulta = new Consulta();
        consulta.setId(consultaDto.getId());
        consulta.setFechaHora(consultaDto.getFechaHora());
        consulta.setDiagnostico(consultaDto.getDiagnostico());
        if (consultaDto.getPaciente() != null) {
            Paciente paciente = new Paciente();
            paciente.setId(consultaDto.getPaciente().getId());
            paciente.setNombre(consultaDto.getPaciente().getNombre());
            paciente.setApellido(consultaDto.getPaciente().getApellido());
            paciente.setTelefono(consultaDto.getPaciente().getTelefono());
            paciente.setDireccion(consultaDto.getPaciente().getDireccion());
            paciente.setFechaNacimiento(consultaDto.getPaciente().getFechaNacimiento());
            consulta.setPaciente(paciente);
        }
        if (consultaDto.getTratamientos() != null) {
            List<Tratamiento> tratamientos = new ArrayList<>();
            for (TratamientoDto tratamientoDto : consultaDto.getTratamientos()) {
                Tratamiento tratamiento = new Tratamiento();
                tratamiento.setId(tratamientoDto.getId());
                tratamiento.setNombre(tratamientoDto.getNombre());
                tratamiento.setDescripcion(tratamientoDto.getDescripcion());
                // Agregar más campos según sea necesario
                tratamientos.add(tratamiento);
            }
            consulta.setTratamientos(tratamientos);
        }
        return consulta;
    }
}