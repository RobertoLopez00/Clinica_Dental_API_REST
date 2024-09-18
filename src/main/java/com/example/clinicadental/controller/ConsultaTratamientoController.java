package com.example.clinicadental.controller;


import com.example.clinicadental.dto.ConsultaTratamientoDto;
import com.example.clinicadental.services.ConsultaTratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consulta-tratamientos")
public class ConsultaTratamientoController {

    @Autowired
    private ConsultaTratamientoService consultaTratamientoService;

    @GetMapping
    public List<ConsultaTratamientoDto> getAllConsultaTratamientos() {
        return consultaTratamientoService.getAllConsultaTratamientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaTratamientoDto> getConsultaTratamientoById(@PathVariable Integer id) {
        Optional<ConsultaTratamientoDto> consultaTratamientoDto = consultaTratamientoService.getConsultaTratamientoById(id);
        return consultaTratamientoDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConsultaTratamientoDto createConsultaTratamiento(@RequestBody ConsultaTratamientoDto consultaTratamientoDto) {
        return consultaTratamientoService.createConsultaTratamiento(consultaTratamientoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaTratamientoDto> updateConsultaTratamiento(@PathVariable Integer id, @RequestBody ConsultaTratamientoDto consultaTratamientoDto) {
        Optional<ConsultaTratamientoDto> updatedConsultaTratamientoDto = consultaTratamientoService.updateConsultaTratamiento(id, consultaTratamientoDto);
        return updatedConsultaTratamientoDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultaTratamiento(@PathVariable Integer id) {
        consultaTratamientoService.deleteConsultaTratamiento(id);
        return ResponseEntity.noContent().build();
    }
}
