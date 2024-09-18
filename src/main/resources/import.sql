-- Insertar registros en la tabla dentista
INSERT INTO dentista (id, nombre, apellido, direccion, telefono) VALUES (1, 'Juan', 'Perez', 'Calle Falsa 123', '123456789');
INSERT INTO dentista (id, nombre, apellido, direccion, telefono) VALUES (2, 'Ana', 'Gomez', 'Avenida Siempre Viva 456', '987654321');

-- Insertar registros en la tabla paciente
INSERT INTO paciente (id, nombre, apellido, telefono, direccion, fecha_nacimiento) VALUES (1, 'Carlos', 'Lopez', '111222333', 'Calle Luna 789', '1980-01-01');
INSERT INTO paciente (id, nombre, apellido, telefono, direccion, fecha_nacimiento) VALUES (2, 'Maria', 'Martinez', '444555666', 'Calle Sol 101', '1990-02-02');

-- Insertar registros en la tabla cita con el estado "Activo"
INSERT INTO cita (id, dentista_id, paciente_id, fecha_hora, estado, motivo) VALUES (1, 1, 1, '2023-10-01 10:00:00', 'Activo', 'Consulta general');
INSERT INTO cita (id, dentista_id, paciente_id, fecha_hora, estado, motivo) VALUES (2, 2, 2, '2023-10-02 11:00:00', 'Activo', 'Limpieza dental');

-- Insertar registros en la tabla horario
INSERT INTO horario (id, dias, hora_entrada, hora_salida, dentista_id) VALUES (1, 'Lunes', '08:00:00', '12:00:00', 1);
INSERT INTO horario (id, dias, hora_entrada, hora_salida, dentista_id) VALUES (2, 'Martes', '14:00:00', '18:00:00', 2);

-- Insertar registros en la tabla especialidad
INSERT INTO especialidad (id, nombre) VALUES (1, 'Ortodoncia');
INSERT INTO especialidad (id, nombre) VALUES (2, 'Endodoncia');

-- Insertar registros en la tabla dentista_especialidad
INSERT INTO dentista_especialidad (dentista_id, especialidad_id) VALUES (1, 1);
INSERT INTO dentista_especialidad (dentista_id, especialidad_id) VALUES (2, 2);

-- Insertar registros en la tabla consulta
INSERT INTO consulta (id, fecha_hora, diagnostico, paciente_id) VALUES (1, '2023-10-01 10:00:00', 'Consulta inicial', 1);
INSERT INTO consulta (id, fecha_hora, diagnostico, paciente_id) VALUES (2, '2023-10-02 11:00:00', 'Revisión', 2);

-- Insertar registros en la tabla detalle_paciente
INSERT INTO detalle_paciente (id, detalle, paciente_id) VALUES (1, 'Alergia a penicilina', 1);
INSERT INTO detalle_paciente (id, detalle, paciente_id) VALUES (2, 'Diabetes', 2);

-- Insertar registros en la tabla tratamiento
INSERT INTO tratamiento (id, nombre, descripcion) VALUES (1, 'Tratamiento A', 'Descripción del tratamiento A');
INSERT INTO tratamiento (id, nombre, descripcion) VALUES (2, 'Tratamiento B', 'Descripción del tratamiento B');

-- Insertar registros en la tabla consulta_tratamiento
INSERT INTO consulta_tratamiento (consulta_id, tratamiento_id) VALUES (1, 1);
INSERT INTO consulta_tratamiento (consulta_id, tratamiento_id) VALUES (1, 2);
INSERT INTO consulta_tratamiento (consulta_id, tratamiento_id) VALUES (2, 1);

-- Insertar registros en la tabla users y roles
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');

INSERT INTO `db_clinica`.`users` (`enabled`, `id`, `username`, `password`) VALUES (1, 1, 'roberto', '$2a$10$JDQIC24AMy9gN1tNWcOso.2cmoCIvHbdxp1asYclm1BxWKr90.15i');
INSERT INTO `db_clinica`.`users` (`enabled`, `id`, `username`, `password`) VALUES (1, 2, 'rodrigo', '$2a$10$JDQIC24AMy9gN1tNWcOso.2cmoCIvHbdxp1asYclm1BxWKr90.15i');
