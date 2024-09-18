package com.example.clinicadental.repositories;

import com.example.clinicadental.entities.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends CrudRepository<Cita, Integer> {

    //Enseña las citas que estan en un rango de fechas, en este caso, entre startDate y endDate, pusimos que sea entre todo el año 2023
    @Query("SELECT c FROM Cita c WHERE c.fechaHora = :fechaHora")
    List<Cita> findCitasByFechaHora(@Param("fechaHora") LocalDateTime fechaHora);

    @Query("SELECT c FROM Cita c WHERE c.paciente.id = :pacienteId")
    List<Cita> findCitasByPacienteId(@Param("pacienteId") Integer pacienteId);

    @Query("SELECT COUNT(c) FROM Cita c WHERE c.motivo = :motivo")
    Long countCitasByMotivo(@Param("motivo") String motivo);

    @Query("SELECT c FROM Cita c WHERE c.fechaHora BETWEEN :startDate AND :endDate")
    List<Cita> findCitasBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT c FROM Cita c WHERE c.dentista.id = :dentistaId")
    List<Cita> findCitasByDentistaId(@Param("dentistaId") Integer dentistaId);

    @Query("SELECT c FROM Cita c WHERE c.estado != 'eliminado'")
    List<Cita> findAllActive();
}
