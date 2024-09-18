package com.example.clinicadental.services.Implementaciones;

import com.example.clinicadental.dto.DentistaDto;
import com.example.clinicadental.dto.HorarioDto;
import com.example.clinicadental.entities.Dentista;
import com.example.clinicadental.entities.Horario;
import com.example.clinicadental.repositories.HorarioRepository;
import com.example.clinicadental.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public List<HorarioDto> getAllHorarios() {
        List<Horario> horarios = (List<Horario>) horarioRepository.findAll();
        List<HorarioDto> horarioDtos = new ArrayList<>();
        for (Horario horario : horarios) {
            horarioDtos.add(convertToDto(horario));
        }
        return horarioDtos;
    }

    @Override
    public Optional<HorarioDto> getHorarioById(Integer id) {
        Optional<Horario> horario = horarioRepository.findById(id);
        return horario.map(this::convertToDto);
    }

    @Override
    public HorarioDto createHorario(HorarioDto horarioDto) {
        Horario horario = convertToEntity(horarioDto);
        horario = horarioRepository.save(horario);
        return convertToDto(horario);
    }

    @Override
    public Optional<HorarioDto> updateHorario(Integer id, HorarioDto horarioDto) {
        if (horarioRepository.existsById(id)) {
            Horario horario = convertToEntity(horarioDto);
            horario.setId(id);
            horario = horarioRepository.save(horario);
            return Optional.of(convertToDto(horario));
        }
        return Optional.empty();
    }

    @Override
    public void deleteHorario(Integer id) {
        horarioRepository.deleteById(id);
    }

    private HorarioDto convertToDto(Horario horario) {
        HorarioDto horarioDto = new HorarioDto();
        horarioDto.setId(horario.getId());
        horarioDto.setDias(horario.getDias());
        horarioDto.setHoraEntrada(LocalTime.parse(horario.getHoraEntrada(), TIME_FORMATTER));
        horarioDto.setHoraSalida(LocalTime.parse(horario.getHoraSalida(), TIME_FORMATTER));
        horarioDto.setDentista(horario.getDentista() != null ? convertDentistaToDto(horario.getDentista()) : null);
        return horarioDto;
    }

    private Horario convertToEntity(HorarioDto horarioDto) {
        Horario horario = new Horario();
        horario.setId(horarioDto.getId());
        horario.setDias(horarioDto.getDias());
        horario.setHoraEntrada(horarioDto.getHoraEntrada().format(TIME_FORMATTER));
        horario.setHoraSalida(horarioDto.getHoraSalida().format(TIME_FORMATTER));
        horario.setDentista(horarioDto.getDentista() != null ? convertDentistaToEntity(horarioDto.getDentista()) : null);
        return horario;
    }

    private DentistaDto convertDentistaToDto(Dentista dentista) {
        DentistaDto dentistaDto = new DentistaDto();
        dentistaDto.setId(dentista.getId());
        dentistaDto.setNombre(dentista.getNombre());
        dentistaDto.setApellido(dentista.getApellido());
        dentistaDto.setDireccion(dentista.getDireccion());
        dentistaDto.setTelefono(dentista.getTelefono());
        return dentistaDto;
    }

    private Dentista convertDentistaToEntity(DentistaDto dentistaDto) {
        Dentista dentista = new Dentista();
        dentista.setId(dentistaDto.getId());
        dentista.setNombre(dentistaDto.getNombre());
        dentista.setApellido(dentistaDto.getApellido());
        dentista.setDireccion(dentistaDto.getDireccion());
        dentista.setTelefono(dentistaDto.getTelefono());
        return dentista;
    }
}