package com.example.clinicadental.dto;

import java.time.LocalTime;

public class HorarioDto {
    private Integer id;
    private String dias;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private DentistaDto dentista;

    public HorarioDto() {
    }

    public HorarioDto(Integer id, String dias, LocalTime horaEntrada, LocalTime horaSalida, DentistaDto dentista) {
        this.id = id;
        this.dias = dias;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.dentista = dentista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public DentistaDto getDentista() {
        return dentista;
    }

    public void setDentista(DentistaDto dentista) {
        this.dentista = dentista;
    }
}
