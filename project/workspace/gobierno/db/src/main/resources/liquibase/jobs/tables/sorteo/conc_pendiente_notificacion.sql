CREATE TABLE conc_pendiente_notificacion (
  id_sorteo                      BIGINT   NOT NULL
	, id_concesionaria             BIGINT   NOT NULL
	, PRIMARY KEY(id_sorteo, id_concesionaria)
	, FOREIGN KEY(id_sorteo) REFERENCES sorteos(id)
	, FOREIGN KEY(id_concesionaria) REFERENCES concesionaria(id)
);

-- tabla encargada de tener la lista de concesionarias notificadas para un sorteo