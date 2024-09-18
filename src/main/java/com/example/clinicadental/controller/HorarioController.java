package com.example.clinicadental.controller;

import com.example.clinicadental.dto.HorarioDto;
import com.example.clinicadental.services.HorarioService;
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
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public List<HorarioDto> list() {
        return horarioService.getAllHorarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Integer id) {
        Optional<HorarioDto> horarioOptional = horarioService.getHorarioById(id);
        if (horarioOptional.isPresent()) {
            return ResponseEntity.ok(horarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody HorarioDto horarioDto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioService.createHorario(horarioDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody HorarioDto horarioDto, BindingResult result, @PathVariable Integer id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<HorarioDto> horarioOptional = horarioService.updateHorario(id, horarioDto);
        if (horarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(horarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        horarioService.deleteHorario(id);
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