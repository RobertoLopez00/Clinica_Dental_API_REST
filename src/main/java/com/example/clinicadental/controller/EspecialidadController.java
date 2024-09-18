package com.example.clinicadental.controller;

import com.example.clinicadental.dto.EspecialidadDto;
import com.example.clinicadental.services.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<EspecialidadDto>> getAllEspecialidades() {
        List<EspecialidadDto> especialidades = especialidadService.getAllEspecialidades();
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDto> getEspecialidadById(@PathVariable Integer id) {
        Optional<EspecialidadDto> especialidad = especialidadService.getEspecialidadById(id);
        return especialidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EspecialidadDto> createEspecialidad(@RequestBody EspecialidadDto especialidadDto) {
        EspecialidadDto createdEspecialidad = especialidadService.createEspecialidad(especialidadDto);
        return ResponseEntity.ok(createdEspecialidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadDto> updateEspecialidad(@PathVariable Integer id, @RequestBody EspecialidadDto especialidadDto) {
        Optional<EspecialidadDto> updatedEspecialidad = especialidadService.updateEspecialidad(id, especialidadDto);
        return updatedEspecialidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Integer id) {
        especialidadService.deleteEspecialidad(id);
        return ResponseEntity.noContent().build();
    }
}