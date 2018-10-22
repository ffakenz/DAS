CREATE TABLE participantes (
	id_sorteo BIGINT NOT NULL
	, id_plan BIGINT NOT NULL
	, estado VARCHAR(100) NOT NULL DEFAULT 'participante'
	, PRIMARY KEY (id_sorteo, id_plan)
	, FOREIGN KEY(id_sorteo) REFERENCES sorteos(id)
	, FOREIGN KEY(id_plan) REFERENCES estado_cuentas(id)
	, FOREIGN KEY(estado) REFERENCES estado_participante(nombre)
);