package com.example.clinicadental.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaDto {

    private Integer id;
    private LocalDateTime fechaHora;
    private String diagnostico;
    private PacienteDto paciente;
    private List<TratamientoDto> tratamientos;

    public ConsultaDto() {
    }

    public ConsultaDto(Integer id, LocalDateTime fechaHora, String diagnostico, PacienteDto paciente, List<TratamientoDto> tratamientos) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.diagnostico = diagnostico;
        this.paciente = paciente;
        this.tratamientos = tratamientos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public List<TratamientoDto> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<TratamientoDto> tratamientos) {
        this.tratamientos = tratamientos;
    }
}
