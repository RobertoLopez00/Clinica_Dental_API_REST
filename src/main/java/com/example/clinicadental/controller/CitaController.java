package com.example.clinicadental.controller;

import com.example.clinicadental.dto.CitaDto;
import com.example.clinicadental.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaDto> createCita(@RequestBody CitaDto citaDto) {
        CitaDto createdCita = citaService.createCita(citaDto);
        return ResponseEntity.ok(createdCita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDto> getCitaById(@PathVariable Integer id) {
        Optional<CitaDto> citaDto = citaService.getCitaById(id);
        return citaDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CitaDto>> getAllCitas() {
        List<CitaDto> citas = citaService.getAllActiveCitas();
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDto> updateCita(@PathVariable Integer id, @RequestBody CitaDto citaDto) {
        Optional<CitaDto> updatedCita = citaService.updateCita(id, citaDto);
        return updatedCita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Integer id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}