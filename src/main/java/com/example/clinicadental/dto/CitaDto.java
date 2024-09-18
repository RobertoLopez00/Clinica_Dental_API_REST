package com.example.clinicadental.dto;

import java.time.LocalDateTime;

public class CitaDto {
    private Integer id;
    private LocalDateTime fechaHora;
    private String motivo;
    private PacienteDto paciente;
    private DentistaDto dentista;
    private String estado = "Activo";

    public CitaDto() {
    }

    public CitaDto(Integer id, LocalDateTime fechaHora, String motivo, PacienteDto paciente, DentistaDto dentista, String estado) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.paciente = paciente;
        this.dentista = dentista;
        this.estado = estado;
    }

    // Getters and Setters
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public DentistaDto getDentista() {
        return dentista;
    }

    public void setDentista(DentistaDto dentista) {
        this.dentista = dentista;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}