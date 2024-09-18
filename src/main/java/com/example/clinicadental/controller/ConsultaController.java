package com.example.clinicadental.controller;

import com.example.clinicadental.dto.ConsultaDto;
import com.example.clinicadental.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDto>> getAllConsultas() {
        List<ConsultaDto> consultas = consultaService.getAllConsultas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> getConsultaById(@PathVariable Integer id) {
        Optional<ConsultaDto> consulta = consultaService.getConsultaById(id);
        return consulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsultaDto> createConsulta(@RequestBody ConsultaDto consultaDto) {
        ConsultaDto createdConsulta = consultaService.createConsulta(consultaDto);
        return ResponseEntity.ok(createdConsulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> updateConsulta(@PathVariable Integer id, @RequestBody ConsultaDto consultaDto) {
        Optional<ConsultaDto> updatedConsulta = consultaService.updateConsulta(id, consultaDto);
        return updatedConsulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteConsulta(@PathVariable Integer id) {
        consultaService.deleteConsulta(id);
    }
}