package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.CitaDto;
import com.example.clinicadental.dto.DentistaDto;
import com.example.clinicadental.dto.EspecialidadDto;
import com.example.clinicadental.dto.PacienteDto;
import com.example.clinicadental.entities.Cita;
import com.example.clinicadental.entities.Dentista;
import com.example.clinicadental.entities.Especialidad;
import com.example.clinicadental.entities.Paciente;
import com.example.clinicadental.repositories.CitaRepository;
import com.example.clinicadental.services.CitaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public CitaDto createCita(CitaDto citaDto) {
        Cita cita = convertToEntity(citaDto);
        if (cita.getEstado() == null || cita.getEstado().isEmpty()) {
            cita.setEstado("activo");
        }
        Cita savedCita = citaRepository.save(cita);
        return convertToDto(savedCita);
    }

    @Override
    public Optional<CitaDto> getCitaById(Integer id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita.map(this::convertToDto);
    }

    @Override
    public List<CitaDto> getAllActiveCitas() {
        List<Cita> citas = citaRepository.findAllActive();
        return citas.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CitaDto> updateCita(Integer id, CitaDto citaDto) {
        if (citaRepository.existsById(id)) {
            Cita cita = convertToEntity(citaDto);
            cita.setId(id);
            Cita updatedCita = citaRepository.save(cita);
            return Optional.of(convertToDto(updatedCita));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteCita(Integer id) {
        Cita cita = citaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado("eliminado");
        citaRepository.save(cita);
    }

    private CitaDto convertToDto(Cita cita) {
        CitaDto citaDto = new CitaDto();
        citaDto.setId(cita.getId());
        citaDto.setFechaHora(cita.getFechaHora());
        citaDto.setMotivo(cita.getMotivo());
        citaDto.setEstado(cita.getEstado());
        if (cita.getPaciente() != null) {
            PacienteDto pacienteDto = new PacienteDto();
            pacienteDto.setId(cita.getPaciente().getId());
            pacienteDto.setNombre(cita.getPaciente().getNombre());
            pacienteDto.setApellido(cita.getPaciente().getApellido());
            pacienteDto.setTelefono(cita.getPaciente().getTelefono());
            pacienteDto.setDireccion(cita.getPaciente().getDireccion());
            pacienteDto.setFechaNacimiento(cita.getPaciente().getFechaNacimiento());
            citaDto.setPaciente(pacienteDto);
        }
        if (cita.getDentista() != null) {
            DentistaDto dentistaDto = new DentistaDto();
            dentistaDto.setId(cita.getDentista().getId());
            dentistaDto.setNombre(cita.getDentista().getNombre());
            dentistaDto.setApellido(cita.getDentista().getApellido());
            dentistaDto.setTelefono(cita.getDentista().getTelefono());
            dentistaDto.setDireccion(cita.getDentista().getDireccion());
            if (cita.getDentista().getEspecialidades() != null) {
                List<EspecialidadDto> especialidadDtos = new ArrayList<>();
                for (Especialidad especialidad : cita.getDentista().getEspecialidades()) {
                    EspecialidadDto especialidadDto = new EspecialidadDto();
                    especialidadDto.setId(especialidad.getId());
                    especialidadDto.setNombre(especialidad.getNombre());
                    especialidadDtos.add(especialidadDto);
                }
                dentistaDto.setEspecialidades(especialidadDtos);
            }
            citaDto.setDentista(dentistaDto);
        }
        return citaDto;
    }

    private Cita convertToEntity(CitaDto citaDto) {
        Cita cita = new Cita();
        cita.setId(citaDto.getId());
        cita.setFechaHora(citaDto.getFechaHora());
        cita.setMotivo(citaDto.getMotivo());
        cita.setEstado(citaDto.getEstado());
        if (citaDto.getPaciente() != null) {
            Paciente paciente = new Paciente();
            paciente.setId(citaDto.getPaciente().getId());
            paciente.setNombre(citaDto.getPaciente().getNombre());
            paciente.setApellido(citaDto.getPaciente().getApellido());
            paciente.setTelefono(citaDto.getPaciente().getTelefono());
            paciente.setDireccion(citaDto.getPaciente().getDireccion());
            paciente.setFechaNacimiento(citaDto.getPaciente().getFechaNacimiento());
            cita.setPaciente(paciente);
        }
        if (citaDto.getDentista() != null) {
            Dentista dentista = new Dentista();
            dentista.setId(citaDto.getDentista().getId());
            dentista.setNombre(citaDto.getDentista().getNombre());
            dentista.setApellido(citaDto.getDentista().getApellido());
            dentista.setTelefono(citaDto.getDentista().getTelefono());
            dentista.setDireccion(citaDto.getDentista().getDireccion());
            if (citaDto.getDentista().getEspecialidades() != null) {
                List<Especialidad> especialidades = new ArrayList<>();
                for (EspecialidadDto especialidadDto : citaDto.getDentista().getEspecialidades()) {
                    Especialidad especialidad = new Especialidad();
                    especialidad.setId(especialidadDto.getId());
                    especialidad.setNombre(especialidadDto.getNombre());
                    especialidades.add(especialidad);
                }
                dentista.setEspecialidades(especialidades);
            }
            cita.setDentista(dentista);
        }
        return cita;
    }
}