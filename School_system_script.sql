-- ==========================================
-- TABLA: PROFESOR
-- ==========================================
CREATE TABLE profesor (
  id VARCHAR(20) PRIMARY KEY,
  nombre VARCHAR(50),
  correo VARCHAR(100) UNIQUE,
  telefono VARCHAR(20),
  direccion VARCHAR(100),
  titulo_academico VARCHAR(60),
  especialidad VARCHAR(50),
  fecha_ingreso DATE DEFAULT CURRENT_DATE,
  estado VARCHAR(15) DEFAULT 'activo',
  fecha_cambio_estado TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================
-- TABLA: NIVEL ACADÉMICO
-- ==========================================
CREATE TABLE nivel_academico (
  id SERIAL PRIMARY KEY,
  nivel VARCHAR(30) NOT NULL UNIQUE
);

-- ==========================================
-- TABLA: GRADO
-- ==========================================
CREATE TABLE grado (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(20) NOT NULL,
  jornada VARCHAR(15),
  id_profesor_director VARCHAR(20),
  id_nivel_academico INT,
  CONSTRAINT fk_grado_profesor
    FOREIGN KEY (id_profesor_director)
      REFERENCES profesor(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL,
  CONSTRAINT fk_grado_nivel
    FOREIGN KEY (id_nivel_academico)
      REFERENCES nivel_academico(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL
);

-- ==========================================
-- TABLA: INFRAESTRUCTURA
-- ==========================================
CREATE TABLE infraestructura (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(30),
  tipo VARCHAR(30),
  capacidad INT CHECK (capacidad > 0),
  ubicacion VARCHAR(100)
);

-- ==========================================
-- TABLA: ASIGNATURA
-- ==========================================
CREATE TABLE asignatura (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(50),
  id_profesor VARCHAR(20),
  id_grado INT,
  CONSTRAINT fk_asignatura_profesor
    FOREIGN KEY (id_profesor)
      REFERENCES profesor(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL,
  CONSTRAINT fk_asignatura_grado
    FOREIGN KEY (id_grado)
      REFERENCES grado(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL
);

-- ==========================================
-- TABLA: HORARIOS
-- ==========================================
CREATE TABLE horarios (
  id_horario SERIAL PRIMARY KEY,
  id_grado INT,
  id_asignatura INT,
  id_infra INT,
  dia_semana VARCHAR(15),
  hora_inicio TIME NOT NULL,
  hora_fin TIME NOT NULL,
  CONSTRAINT fk_horarios_grado
    FOREIGN KEY (id_grado)
      REFERENCES grado(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
  CONSTRAINT fk_horarios_infra
    FOREIGN KEY (id_infra)
      REFERENCES infraestructura(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL,
  CONSTRAINT fk_horarios_asignatura
    FOREIGN KEY (id_asignatura)
      REFERENCES asignatura(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

-- ==========================================
-- TABLA: ESTUDIANTE
-- ==========================================
CREATE TABLE estudiante (
  id VARCHAR(20) PRIMARY KEY,
  nombre VARCHAR(50),
  apellido VARCHAR(50),
  fecha_nacimiento DATE,
  nacionalidad VARCHAR(30),
  lugar_nacimiento VARCHAR(50),
  discapacidad BOOLEAN DEFAULT FALSE,
  desc_discapacidad TEXT,
  correo VARCHAR(100) UNIQUE,
  telefono VARCHAR(20),
  direccion VARCHAR(100),
  fecha_ingreso DATE DEFAULT CURRENT_DATE,
  fecha_matricula DATE,
  grado_actual INT,
  estado VARCHAR(15) DEFAULT 'activo',
  fecha_cambio_estado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_estudiante_grado
    FOREIGN KEY (grado_actual)
      REFERENCES grado(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL
);

-- ==========================================
-- TABLA: ACUDIENTE
-- ==========================================
CREATE TABLE acudiente (
  id VARCHAR(20) PRIMARY KEY,
  nombre VARCHAR(50),
  correo VARCHAR(100),
  telefono VARCHAR(20),
  telefono_adic VARCHAR(20),
  direccion VARCHAR(100),
  fecha_de_nacimiento DATE,
  estado_civil VARCHAR(20),
  nivel_educativo VARCHAR(30),
  ocupacion VARCHAR(50),
  vive_con_estudiante BOOLEAN DEFAULT TRUE,
  id_estudiante VARCHAR(20),
  CONSTRAINT fk_acudiente_estudiante
    FOREIGN KEY (id_estudiante)
      REFERENCES estudiante(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

-- ==========================================
-- TABLA: NOTAS
-- ==========================================
CREATE TABLE notas (
  id SERIAL PRIMARY KEY,
  id_estudiante VARCHAR(20),
  id_asignatura INT,
  periodo VARCHAR(10),
  calificacion NUMERIC(1,2),
  CONSTRAINT fk_notas_asignatura
    FOREIGN KEY (id_asignatura)
      REFERENCES asignatura(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
  CONSTRAINT fk_notas_estudiante
    FOREIGN KEY (id_estudiante)
      REFERENCES estudiante(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

-- ==========================================
-- TABLA: USUARIO
-- ==========================================
CREATE TABLE usuario (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  contrasena VARCHAR(100) NOT NULL,
  rol VARCHAR(20),
  id_estudiante VARCHAR(20),
  id_profesor VARCHAR(20),
  estado VARCHAR(15) DEFAULT 'activo',
  CONSTRAINT fk_usuario_estudiante
    FOREIGN KEY (id_estudiante)
      REFERENCES estudiante(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL,
  CONSTRAINT fk_usuario_profesor
    FOREIGN KEY (id_profesor)
      REFERENCES profesor(id)
      ON UPDATE CASCADE
      ON DELETE SET NULL
);
