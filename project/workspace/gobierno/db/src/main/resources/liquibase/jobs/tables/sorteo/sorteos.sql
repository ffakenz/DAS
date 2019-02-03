CREATE TABLE sorteos (
	id                      BIGINT                  IDENTITY
	, estado                VARCHAR(100)   NOT NULL DEFAULT 'nuevo'
	, fecha_ejecucion       DATETIME       NOT NULL
	, mes_sorteo AS CAST (
        DATEPART(MONTH, fecha_ejecucion) AS INT
	)
	, PRIMARY KEY(id)
	, FOREIGN KEY(estado) REFERENCES estado_sorteo(nombre)
);