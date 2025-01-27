// src/main/java/com/example/clinicadental/dto/DentistaDto.java
package com.example.clinicadental.dto;

import java.util.List;

public class DentistaDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private List<EspecialidadDto> especialidades;

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<EspecialidadDto> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadDto> especialidades) {
        this.especialidades = especialidades;
    }
}