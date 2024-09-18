package com.example.clinicadental.repositories;

import com.example.clinicadental.entities.ConsultaTratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaTratamientoRepository extends JpaRepository<ConsultaTratamiento, Integer> {
    void deleteByConsultaId(Integer consultaId);
}