package com.example.clinicadental.repositories;

import com.example.clinicadental.entities.Consulta;
import org.springframework.data.repository.CrudRepository;

public interface ConsultaRepository extends CrudRepository<Consulta, Integer> {
    void deleteById(Integer id);
}
