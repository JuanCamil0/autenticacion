CREATE TABLE usuario (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    documento_identidad VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(500),
    telefono VARCHAR(20),
    id_rol INTEGER NOT NULL,
    salario_base DECIMAL(12,2) NOT NULL CHECK (salario_base >= 0 AND salario_base <= 15000000)
);

-- Create index for common queries
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_usuario_documento ON usuario(documento_identidad);