CREATE TABLE sorteos (
	id                      BIGINT                  IDENTITY
	, estado                VARCHAR(100)   NOT NULL DEFAULT 'nuevo'
	, fecha_ejecucion       DATE       NOT NULL
	, mes_sorteo AS CAST (
        DATEPART(MONTH, fecha_ejecucion) AS INT
	)
	, anio_sorteo AS CAST (
        DATEPART(YEAR, fecha_ejecucion) AS INT
	)
	, dia_sorteo AS CAST (
        DATEPART(DAY, fecha_ejecucion)  AS INT
	)
	, fecha_creacion                    DATE    DEFAULT GETDATE()
	, PRIMARY KEY(id)
	, FOREIGN KEY(estado) REFERENCES estado_sorteo(nombre)
	, UNIQUE(fecha_ejecucion)
);