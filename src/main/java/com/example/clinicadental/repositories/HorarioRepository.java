// src/main/java/com/example/clinicadental/repositories/HorarioRepository.java
package com.example.clinicadental.repositories;

import com.example.clinicadental.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    void deleteByDentistaId(Integer dentistaId);
}