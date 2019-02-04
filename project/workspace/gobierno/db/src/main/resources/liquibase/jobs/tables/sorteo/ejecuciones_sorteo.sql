CREATE TABLE ejecuciones_sorteo (
	id                      BIGINT                  IDENTITY
	, id_sorteo             BIGINT         NOT NULL
	, estado                VARCHAR(100)   NOT NULL
	, fecha                 DATETIME       NOT NULL DEFAULT GETDATE()
	, comments              VARCHAR(2000)  NULL     DEFAULT
	, PRIMARY KEY(id)
	, FOREIGN KEY(estado)    REFERENCES estado_sorteo(nombre)
	, FOREIGN KEY(id_sorteo) REFERENCES sorteos(id)
);