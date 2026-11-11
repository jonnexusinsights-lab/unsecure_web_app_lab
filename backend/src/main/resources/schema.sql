-- ==============================================================================
-- Esquema de Base de Datos - InsecurePay Digital Banking Portal
-- ==============================================================================

DROP TABLE IF EXISTS TRANSACCIONES;
DROP TABLE IF EXISTS CUENTAS_BANCARIAS;
DROP TABLE IF EXISTS USUARIOS;

CREATE TABLE USUARIOS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL, -- [FALLO CRIPTOGRAFICO]: Contrasenas en texto plano
    nombre_completo VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE CUENTAS_BANCARIAS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    numero_cuenta VARCHAR(30) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    moneda VARCHAR(3) NOT NULL DEFAULT 'CRC',
    activa BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_cuenta_usuario FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id)
);

CREATE TABLE TRANSACCIONES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cuenta_origen_id BIGINT NOT NULL,
    cuenta_destino_id BIGINT NOT NULL,
    monto DECIMAL(15,2) NOT NULL,
    concepto_html CLOB, -- [FALLO DE INYECCION]: Almacena HTML/Javascript arbitrario sin sanitizar
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) NOT NULL DEFAULT 'COMPLETADA',
    CONSTRAINT fk_transaccion_origen FOREIGN KEY (cuenta_origen_id) REFERENCES CUENTAS_BANCARIAS(id),
    CONSTRAINT fk_transaccion_destino FOREIGN KEY (cuenta_destino_id) REFERENCES CUENTAS_BANCARIAS(id)
);

