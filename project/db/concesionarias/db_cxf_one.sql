USE db_cxf_one;

DROP TABLE detalle_cuotas;
DROP TABLE planes;
DROP TABLE clientes;
DROP TABLE vehiculos;
DROP TABLE tipos_vehiculo;

CREATE TABLE tipos_vehiculo (
	nombre VARCHAR(100) PRIMARY KEY 
);
INSERT INTO tipos_vehiculo(nombre)
VALUES('taxi')
	,('particular')
	,('comercial')
	,('camion')
	,('utilitario');

CREATE TABLE vehiculos (
	id INT IDENTITY PRIMARY KEY
	, tipo VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES tipos_vehiculo(nombre)
	, nombre VARCHAR(100) NOT NULL
	, marca VARCHAR(100) NOT NULL
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, precio BIGINT NOT NULL 
	, color VARCHAR(100) NOT NULL
	, modelo VARCHAR(100) NOT NULL
	, UNIQUE(tipo, nombre)
);
INSERT INTO vehiculos(tipo, nombre, precio, marca, modelo, color)
VALUES ('taxi', 'Corsa', 10000, 'chevrolet', 'v1','c1' )
	, ('particular', 'Gol', 10000, 'volkswagen', 'v3','c2' )
	,('particular', 'Clio', 10000, 'renault', 'v1','c1' )
	, ('comercial', '208', 10000, 'peugeot', 'v1','c4' )
	, ('comercial', 'Focus', 10000, 'ford', 'v5','c3' )
	, ('utilitario', 'Fiorino', 10000, 'fiat', 'v1','c1' )
	, ('utilitario', 'Saveiro', 10000, 'volkswagen', 'v2','c1' )
;

CREATE TABLE clientes (
	id_cliente INT IDENTITY NOT NULL
	, documento BIGINT NOT NULL
	, nombre VARCHAR(100) NOT NULL
	, apellido VARCHAR(100) NOT NULL
	, nro_telefono VARCHAR(20)
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, PRIMARY KEY(id_cliente) -- unico por consecionaria
);
-- after actualization process
INSERT INTO clientes(documento, nombre)
VALUES (100, 'Pedro')
	,(200, 'Juan')
	,(300, 'Franco')
	,(400, 'Nacho')
;

CREATE TABLE planes (
	id INT IDENTITY PRIMARY KEY
	, cliente INT NOT NULL 
	, vehiculo INT FOREIGN KEY REFERENCES vehiculos(id)
	, cant_cuotas_pagas INT NOT NULL DEFAULT 0 -- se actualiza por cada actualizacion
	, nombre VARCHAR(100) NOT NULL
	, fecha_alta DATETIME NOT NULL -- informado por la consecionaria
	, fecha_ultima_actualizacion DATETIME NOT NULL DEFAULT GETDATE() -- se actualiza por cada actualizacion
	, FOREIGN KEY(cliente) REFERENCES clientes(id_cliente)
	, UNIQUE(vehiculo, cliente, fecha_alta)
);
INSERT INTO planes(cliente, vehiculo, fecha_alta, nombre)
VALUES (1, 1, '2018-02-08 20:58:00', 'plan1')
	,(1, 2, '2018-02-08 20:58:00', 'plan2')
	,(2, 2, '2018-02-08 20:58:00', 'plan2')
	,(3, 3, '2018-02-08 20:58:00', 'plan3')
;

CREATE TABLE detalle_cuotas (
	id_plan INT FOREIGN KEY REFERENCES planes(id)
	, id_cuota INT NOT NULL
	, fecha_pago DATETIME NULL
	, fecha_vencimiento DATETIME NOT NULL
	, PRIMARY KEY (id_plan, id_cuota)
);

GO
DROP VIEW compradores;
GO
CREATE VIEW compradores AS
SELECT 
	p.id AS planId
	, p.cant_cuotas_pagas
	, p.fecha_alta
	, p.fecha_ultima_actualizacion
	, v.nombre AS vehiculo
	, c.documento
	, c.nombre
FROM planes p
	INNER JOIN vehiculos v ON p.vehiculo = v.id
	INNER JOIN clientes c ON c.id_cliente = p.cliente;


-- cancelarPlan
	GO
	DROP PROCEDURE cancelarPlan;
	GO
	CREATE PROCEDURE cancelarPlan(@idPlan INT) AS
	UPDATE compradores
	set cant_cuotas_pagas = 60
	WHERE planId = @idPlan;

-- consultarPlan
	SELECT *
	FROM compradores
	WHERE planId = @idPlan;

-- consultarPlanes
	SELECT *
	FROM compradores;