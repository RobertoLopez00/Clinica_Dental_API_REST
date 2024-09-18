package com.example.clinicadental.controller;

import com.example.clinicadental.dto.PacienteDto;
import com.example.clinicadental.entities.Paciente;
import com.example.clinicadental.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<PacienteDto> list() {
        return pacienteService.getAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<PacienteDto> pacienteOptional = pacienteService.getPacienteById(id);
        if (pacienteOptional.isPresent()) {
            return ResponseEntity.ok(pacienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PacienteDto pacienteDto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.createPaciente(pacienteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody PacienteDto pacienteDto, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<PacienteDto> pacienteOptional = pacienteService.updatePaciente(id, pacienteDto);
        if (pacienteOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pacienteService.deletePaciente(id.intValue());
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}