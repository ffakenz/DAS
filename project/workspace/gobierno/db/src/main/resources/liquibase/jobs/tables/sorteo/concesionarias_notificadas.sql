CREATE TABLE concesionarias_notificadas (
  id_sorteo                      BIGINT   IDENTITY
	, id_concesionaria             BIGINT   NOT NULL
	, PRIMARY KEY(id_sorteo, id_concesionaria)
	, FOREIGN KEY(id_sorteo) REFERENCES sorteos(id)
	, FOREIGN KEY(id_concesionaria) REFERENCES concesionaria(id)
);

-- tabla encargada de tener la lista de concesionarias notificadas para un sorteo