CREATE TABLE ganadores (
	id BIGINT IDENTITY PRIMARY KEY
	, sorteo_id BIGINT NOT NULL FOREIGN KEY REFERENCES sorteos(id)
	, plan_id BIGINT NOT NULL FOREIGN KEY REFERENCES estado_cuentas(id)
	, estado VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES estado_ganador(nombre) DEFAULT 'pendiente'
);