CREATE TABLE IF NOT EXISTS paciente (
    id IDENTITY PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    diagnostico VARCHAR(255)
);