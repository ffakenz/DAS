CREATE TABLE sorteos (
	id BIGINT IDENTITY
	, mes_sorteo INT -- DEFAULT DATEPART(MONTH, GETDATE())
	, estado VARCHAR(100) NOT NULL DEFAULT 'nuevo'
	, fecha_ejecucion DATETIME NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY(estado) REFERENCES estado_sorteo(nombre)
);