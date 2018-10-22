CREATE TABLE participantes (
	id_sorteo BIGINT NOT NULL FOREIGN KEY REFERENCES sorteos(id)
	, id_cliente BIGINT NOT NULL
	, id_concesionaria BIGINT NOT NULL
	, PRIMARY KEY (id_sorteo, id_cliente, id_concesionaria)
	, FOREIGN KEY (id_cliente) REFERENCES consumers(id)
	, FOREIGN KEY (id_concesionaria) REFERENCES concesionaria(id)
);