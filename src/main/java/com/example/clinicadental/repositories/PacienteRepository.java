package com.example.clinicadental.repositories;

import com.example.clinicadental.entities.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente, Integer> {
}
