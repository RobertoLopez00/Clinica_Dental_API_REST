package com.example.clinicadental.services.Implementaciones;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.clinicadental.dto.CitaDto;
import com.example.clinicadental.entities.Cita;
import com.example.clinicadental.repositories.CitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

class CitaServiceImplTest {

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private CitaServiceImpl citaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCita() {
        // Crear un objeto CitaDto con los datos de la cita
        CitaDto citaDto = new CitaDto();
        citaDto.setFechaHora(LocalDateTime.parse("2023-10-10T10:00:00"));
        citaDto.setMotivo("Consulta");
        // No establecer el estado para verificar que se establece a "activo" por defecto

        // Crear un objeto Cita que se espera que sea devuelto por el repositorio
        Cita cita = new Cita();
        cita.setId(1);
        cita.setFechaHora(LocalDateTime.parse("2023-10-10T10:00:00"));
        cita.setMotivo("Consulta");
        cita.setEstado("activo");

        // Configurar el comportamiento del mock de citaRepository para que devuelva el objeto Cita cuando se llame al método save
        when(citaRepository.save(any(Cita.class))).thenReturn(cita);

        // Llamar al método createCita de citaService con el objeto CitaDto
        CitaDto savedCitaDto = citaService.createCita(citaDto);

        // Verificar que el objeto savedCitaDto no sea nulo
        assertNotNull(savedCitaDto);
        // Verificar que el estado de la cita sea "activo"
        assertEquals("activo", savedCitaDto.getEstado());
        // Verificar que el método save de citaRepository se haya llamado exactamente una vez con el objeto Cita
        verify(citaRepository, times(1)).save(any(Cita.class));
    }

    @Test
    void testUpdateCita() {
        // Crear un objeto CitaDto con los datos de la cita
        CitaDto citaDto = new CitaDto();
        citaDto.setFechaHora(LocalDateTime.parse("2023-10-10T10:00:00"));
        citaDto.setMotivo("Consulta");
        citaDto.setEstado("completado");

        // Crear un objeto Cita existente
        Cita existingCita = new Cita();
        existingCita.setId(1);
        existingCita.setFechaHora(LocalDateTime.parse("2023-10-10T10:00:00"));
        existingCita.setMotivo("Consulta");
        existingCita.setEstado("completado"); //Si se cambia el estado a "completado" se espera que se actualice, si no da error

        // Configurar el comportamiento del mock de citaRepository
        when(citaRepository.existsById(1)).thenReturn(true);
        when(citaRepository.findById(1)).thenReturn(Optional.of(existingCita));
        when(citaRepository.save(any(Cita.class))).thenReturn(existingCita);

        // Llamar al método updateCita de citaService con el objeto CitaDto
        Optional<CitaDto> updatedCitaDto = citaService.updateCita(1, citaDto);

        // Verificar que el objeto updatedCitaDto no sea nulo
        assertTrue(updatedCitaDto.isPresent());
        // Verificar que el estado de la cita se haya actualizado a "completado"
        assertEquals("completado", updatedCitaDto.get().getEstado());
        // Verificar que el método save de citaRepository se haya llamado exactamente una vez con el objeto Cita
        verify(citaRepository, times(1)).save(any(Cita.class));
    }


}