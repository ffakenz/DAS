CREATE TABLE participantes (
	id_sorteo BIGINT NOT NULL
	, id_plan BIGINT NOT NULL -- TODO: change this to id_estado_cuenta
	, id_concesionaria BIGINT NOT NULL
	, id_consumer BIGINT NOT NULL
	, id_vehiculo BIGINT NOT NULL
	, estado VARCHAR(100) NOT NULL DEFAULT 'participante'
	, PRIMARY KEY (id_sorteo,id_plan,id_concesionaria,id_consumer,id_vehiculo)
	, FOREIGN KEY(id_sorteo) REFERENCES sorteos(id)
	, FOREIGN KEY(id_plan) REFERENCES estado_cuentas(id)
	, FOREIGN KEY(id_concesionaria) REFERENCES concesionaria(id)
	, FOREIGN KEY(id_consumer) REFERENCES consumers(id)
	, FOREIGN KEY(id_vehiculo) REFERENCES vehiculos(id)
	, FOREIGN KEY(estado) REFERENCES estado_participante(nombre)
);