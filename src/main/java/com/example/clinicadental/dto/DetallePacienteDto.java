package com.example.clinicadental.dto;

public class DetallePacienteDto {
    private Integer id;
    private String detalle;
    private PacienteDto paciente;

    public DetallePacienteDto() {
    }

    public DetallePacienteDto(Integer id, String detalle, PacienteDto paciente) {
        this.id = id;
        this.detalle = detalle;
        this.paciente = paciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }
}
