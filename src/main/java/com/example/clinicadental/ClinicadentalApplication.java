package com.example.clinicadental;

import com.example.clinicadental.repositories.CitaRepository;
import com.example.clinicadental.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ClinicadentalApplication implements CommandLineRunner {

	@Autowired
	private CitaRepository citaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClinicadentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
		LocalDateTime endDate = LocalDateTime.of(2023, 12, 31, 23, 59);

		// Imprime citas entre un rango de fechas
		System.out.println("Imprimiendo citas entre un rango de fechas");
		citaRepository.findCitasBetweenDates(startDate, endDate).forEach(System.out::println);

		// Imprime citas en una fecha y hora específica
		// No funciona, no hay citas en esa fecha y hora
		//LocalDateTime specificFechaHora = LocalDateTime.of(2023, 6, 15, 10, 0);
		LocalDateTime specificFechaHora = LocalDateTime.of(2023, 10, 1, 10, 00);
		System.out.println("Imprimiendo citas en una fecha y hora específica");
		citaRepository.findCitasByFechaHora(specificFechaHora).forEach(System.out::println);

		// Imprime citas por pacienteId
		Integer pacienteId = 1;
		System.out.println("Imprimiendo citas por Id del paciente");
		citaRepository.findCitasByPacienteId(pacienteId).forEach(System.out::println);

		// Imprime el conteo de citas por motivo
		String motivo = "Consulta General";
		Long count = citaRepository.countCitasByMotivo(motivo);
		System.out.println("Contador de citas por motivo: '" + motivo + "': " + count);

		// Imprime citas por dentistaId
		Integer dentistaId = 1;
		System.out.println("Imprimiendo citas por Id del dentista");
		citaRepository.findCitasByDentistaId(dentistaId).forEach(System.out::println);
	}
}