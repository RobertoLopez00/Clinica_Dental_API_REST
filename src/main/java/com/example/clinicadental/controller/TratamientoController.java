package com.example.clinicadental.controller;

import com.example.clinicadental.dto.TratamientoDto;
import com.example.clinicadental.entities.Tratamiento;
import com.example.clinicadental.services.TratamientoService;
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
@RequestMapping("/tratamientos")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping
    public List<TratamientoDto> list() {
        return tratamientoService.getAllTratamientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Integer id) {
        Optional<TratamientoDto> tratamientoOptional = tratamientoService.getTratamientoById(id);
        if (tratamientoOptional.isPresent()) {
            return ResponseEntity.ok(tratamientoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TratamientoDto tratamientoDto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamientoService.createTratamiento(tratamientoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TratamientoDto tratamientoDto, BindingResult result, @PathVariable Integer id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<TratamientoDto> tratamientoOptional = tratamientoService.updateTratamiento(id, tratamientoDto);
        if (tratamientoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tratamientoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tratamientoService.deleteTratamiento(id.intValue());
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