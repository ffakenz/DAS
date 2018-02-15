USE das_concesionarias;

DROP TABLE planes;
DROP TABLE clientes;
DROP TABLE concesionarias;
DROP TABLE vehiculos;
DROP TABLE tipos_vehiculo;

CREATE TABLE tipos_vehiculo (
	nombre VARCHAR(100) PRIMARY KEY 
);
INSERT INTO tipos_vehiculo(nombre)
VALUES('taxi'),('particular'),('comercial'),('camion'),('utilitario');

CREATE TABLE vehiculos (
	id INT IDENTITY PRIMARY KEY
	, tipo VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES tipos_vehiculo(nombre)
	, nombre VARCHAR(100) NOT NULL
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, UNIQUE(tipo, nombre)
);
INSERT INTO vehiculos(tipo, nombre)
VALUES ('taxi', 'Corsa')
	, ('particular', 'Gol'), ('particular', 'Clio')
	, ('comercial', '208'), ('comercial', 'Focus')
	, ('utilitario', 'Fiorino'), ('utilitario', 'Saveiro')
;


CREATE TABLE concesionarias (
	id INT IDENTITY PRIMARY KEY
	, nombre VARCHAR(100) NOT NULL
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, UNIQUE(nombre)
);
INSERT INTO concesionarias(nombre)
VALUES('C1'), ('C2'), ('C3'), ('C4'), ('C5');


CREATE TABLE clientes (
	id_concesionaria INT FOREIGN KEY REFERENCES concesionarias(id)
	, correlativo INT NOT NULL -- informado por la consecionaria
	, documento BIGINT NOT NULL
	, id AS CAST(id_concesionaria AS VARCHAR(50)) + '-' + CAST(correlativo AS VARCHAR(50))
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, PRIMARY KEY(correlativo, id_concesionaria) -- unico por consecionaria
);
-- after actualization process
INSERT INTO clientes(id_concesionaria, correlativo, documento)
VALUES(1, 1, 100), (2, 1, 200), (3, 1, 300), (4, 1, 400)
, (1, 2, 500), (2, 2, 600), (3, 2, 700), (4, 2, 800)
, (1, 3, 900), (2, 3, 1000), (3, 3, 1100)
, (1, 4, 1200), (2, 4, 1300)
, (1, 5, 1400)
, (2, 5, 300), (3, 4, 200), (1, 6, 400) -- A) same document in many consecionarias
, (2, 6, 600), (3, 5, 700), (4, 3, 800) -- B) same document in same consecionaria
, (1, 7, 100), (3, 6, 100) -- A && B
;

CREATE TABLE planes (
	id INT IDENTITY PRIMARY KEY
	, concesionaria INT FOREIGN KEY REFERENCES concesionarias(id)
	, cliente INT NOT NULL 
	, vehiculo INT FOREIGN KEY REFERENCES vehiculos(id)
	, cant_cuotas_pagas INT NOT NULL DEFAULT 0 -- se actualiza por cada actualizacion
	, fecha_alta DATETIME NOT NULL -- informado por la consecionaria
	, fecha_ultima_actualizacion DATETIME NOT NULL DEFAULT GETDATE() -- se actualiza por cada actualizacion
	, FOREIGN KEY(cliente, concesionaria) REFERENCES clientes(correlativo, id_concesionaria)
	, UNIQUE(concesionaria, vehiculo, cliente, fecha_alta)
);

INSERT INTO planes(concesionaria, cliente, vehiculo, fecha_alta)
VALUES(1, 1, 1, '2018-02-08 20:58:00')
	,(2, 1, 2, '2018-02-08 20:58:00')
	,(3, 1, 3, '2018-02-08 20:58:00')
	,(4, 1, 4, '2018-02-08 20:58:00')
	,(1, 2, 2, '2018-02-08 20:58:00')
	,(2, 3, 1, '2018-02-08 20:58:00')
	,(3, 2, 2, '2018-02-08 20:58:00');


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
	, c.nombre AS concesionaria
	, c.id AS concesionariaId
	, u.documento
	, u.id AS clientId
FROM planes p
	INNER JOIN vehiculos v ON p.vehiculo = v.id
	INNER JOIN concesionarias c ON p.concesionaria = c.id
	INNER JOIN clientes u ON p.cliente = u.correlativo and p.concesionaria = u.id_concesionaria;


-- cancelarPlan
	GO
	DROP PROCEDURE cancelarPlan;
	GO
	CREATE PROCEDURE cancelarPlan(@idPlan INT) AS
	UPDATE compradores
	set cant_cuotas_pagas = 60
	WHERE planId = @idPlan;

-- consultarPlan
	SELECT 
		planId
		, cant_cuotas_pagas
		, vehiculo
		, concesionaria
		, concesionariaId
		, documento
		, clientId 
	FROM compradores
	WHERE planId = @idPlan;

-- consultarPlanes
	SELECT 
		planId
		, cant_cuotas_pagas
		, vehiculo
		, concesionaria
		, concesionariaId
		, documento
		, clientId 
	FROM compradores;