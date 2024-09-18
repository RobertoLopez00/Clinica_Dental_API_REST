package com.example.clinicadental.controller;

import com.example.clinicadental.dto.DentistaDto;
import com.example.clinicadental.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @GetMapping
    public List<DentistaDto> getAllDentistas() {
        return dentistaService.getAllDentistas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDto> getDentistaById(@PathVariable Integer id) {
        Optional<DentistaDto> dentistaDto = dentistaService.getDentistaById(id);
        return dentistaDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DentistaDto createDentista(@RequestBody DentistaDto dentistaDto) {
        return dentistaService.createDentista(dentistaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaDto> updateDentista(@PathVariable Integer id, @RequestBody DentistaDto dentistaDto) {
        Optional<DentistaDto> updatedDentista = dentistaService.updateDentista(id, dentistaDto);
        return updatedDentista.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentista(@PathVariable Integer id) {
        dentistaService.deleteDentista(id);
        return ResponseEntity.noContent().build();
    }
}