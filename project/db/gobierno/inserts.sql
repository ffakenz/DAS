USE db_gobierno;
GO

INSERT INTO tipos_vehiculo(nombre)
VALUES('taxi'),('particular'),('comercial'),('camion'),('utilitario');

INSERT INTO vehiculos(tipo, nombre)
VALUES ('taxi', 'Corsa')
	, ('particular', 'Gol'), ('particular', 'Clio')
	, ('comercial', '208'), ('comercial', 'Focus')
	, ('utilitario', 'Fiorino'), ('utilitario', 'Saveiro')
;

INSERT INTO config_tecnologicas(nombre)
VALUES('REST'),('CXF'),('AXIS');

INSERT INTO consecionarias(nombre, config)
VALUES('C1', 'REST'), ('C2', 'CXF'), ('C3', 'AXIS'), ('C4', 'REST'), ('C5', 'CXF');

-- after actualization process
INSERT INTO clientes(id_consecionaria, correlativo, documento)
VALUES(1, 1, 100), (2, 1, 200), (3, 1, 300), (4, 1, 400)
, (1, 2, 500), (2, 2, 600), (3, 2, 700), (4, 2, 800)
, (1, 3, 900), (2, 3, 1000), (3, 3, 1100)
, (1, 4, 1200), (2, 4, 1300)
, (1, 5, 1400)
, (2, 5, 300), (3, 4, 200), (1, 6, 400) -- A) same document in many consecionarias
, (2, 6, 600), (3, 5, 700), (4, 3, 800) -- B) same document in same consecionaria
, (1, 7, 100), (3, 6, 100) -- A && B
;

INSERT INTO estado_sorteo(nombre)
VALUES('nuevo'),('pendiente'),('completado');

INSERT INTO sorteos(fecha) VALUES('2018-02-08');	

-- se hace en el proceso de actualizacion c/ 15 dias
-- cada 15 dias 
	-- A) insert
	-- B) update
	INSERT INTO planes(consecionaria, cliente, vehiculo, fecha_alta)
	VALUES(1, 1, 1, '2018-02-08 20:58:00')
		,(2, 1, 2, '2018-02-08 20:58:00')
		,(3, 1, 3, '2018-02-08 20:58:00')
		,(4, 1, 4, '2018-02-08 20:58:00')
		,(1, 2, 2, '2018-02-08 20:58:00')
		,(2, 3, 1, '2018-02-08 20:58:00')
		,(3, 2, 2, '2018-02-08 20:58:00');



INSERT INTO estado_ganador(nombre)
VALUES ('pendiente'), ('cancelado');
-- Registrar ganador
	INSERT INTO ganadores(sorteo_id, plan_id)
	VALUES(1, 1);
	-- constraints:
		-- sorteo es completado
		-- plan al dia y entre cuotas 24 y 36


-- Obtener concesionarias las pendientes de auditoria
	SELECT *
	FROM consecionarias
	WHERE id NOT IN ( 
		SELECT consecionaria
		FROM consecionarias_notificadas
	);