package com.example.clinicadental.repositories;

import com.example.clinicadental.entities.Tratamiento;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TratamientoRepository extends CrudRepository<Tratamiento, Integer> {
    Optional<Tratamiento> findById(Integer id);

}
