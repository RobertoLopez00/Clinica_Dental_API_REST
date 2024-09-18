package com.example.clinicadental.controller;

import com.example.clinicadental.dto.DetallePacienteDto;
import com.example.clinicadental.services.DetallePacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalles")
public class DetallePacienteController {

    @Autowired
    private DetallePacienteService detallePacienteService;

    @GetMapping
    public ResponseEntity<List<DetallePacienteDto>> getAllDetalles() {
        List<DetallePacienteDto> detalles = detallePacienteService.getAllDetalles();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePacienteDto> getDetalleById(@PathVariable Integer id) {
        Optional<DetallePacienteDto> detalle = detallePacienteService.getDetalleById(id);
        return detalle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetallePacienteDto> createDetalle(@RequestBody DetallePacienteDto detallePacienteDto) {
        DetallePacienteDto createdDetalle = detallePacienteService.createDetalle(detallePacienteDto);
        return ResponseEntity.ok(createdDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePacienteDto> updateDetalle(@PathVariable Integer id, @RequestBody DetallePacienteDto detallePacienteDto) {
        Optional<DetallePacienteDto> updatedDetalle = detallePacienteService.updateDetalle(id, detallePacienteDto);
        return updatedDetalle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Integer id) {
        detallePacienteService.deleteDetalle(id);
        return ResponseEntity.noContent().build();
    }
}