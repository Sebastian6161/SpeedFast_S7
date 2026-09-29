-- =====================================================
-- SPEEDFAST - BASE DE DATOS
-- Semana 7
-- =====================================================

CREATE DATABASE IF NOT EXISTS speedfast_db;

USE speedfast_db;

-- =====================================================
-- TABLA REPARTIDOR
-- =====================================================

CREATE TABLE IF NOT EXISTS repartidor (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          nombre VARCHAR(100) NOT NULL
    );

-- =====================================================
-- TABLA PEDIDO
-- =====================================================

CREATE TABLE IF NOT EXISTS pedido (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      direccion VARCHAR(150) NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    estado VARCHAR(20) NOT NULL
    );

-- =====================================================
-- TABLA ENTREGA
-- =====================================================

CREATE TABLE IF NOT EXISTS entrega (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       id_pedido INT NOT NULL,
                                       id_repartidor INT NOT NULL,
                                       fecha DATE NOT NULL,
                                       hora TIME NOT NULL,

                                       CONSTRAINT fk_entrega_pedido
                                       FOREIGN KEY (id_pedido)
    REFERENCES pedido(id),

    CONSTRAINT fk_entrega_repartidor
    FOREIGN KEY (id_repartidor)
    REFERENCES repartidor(id)
    );

-- =====================================================
-- REPARTIDORES INICIALES
-- =====================================================

INSERT INTO repartidor (nombre)
SELECT 'Carlos'
    WHERE NOT EXISTS (
    SELECT 1
    FROM repartidor
    WHERE nombre = 'Carlos'
);

INSERT INTO repartidor (nombre)
SELECT 'María'
    WHERE NOT EXISTS (
    SELECT 1
    FROM repartidor
    WHERE nombre = 'María'
);

INSERT INTO repartidor (nombre)
SELECT 'Pedro'
    WHERE NOT EXISTS (
    SELECT 1
    FROM repartidor
    WHERE nombre = 'Pedro'
);

-- =====================================================
-- CONSULTAS DE COMPROBACIÓN
-- =====================================================

SELECT * FROM repartidor;
SELECT * FROM pedido;
SELECT * FROM entrega;