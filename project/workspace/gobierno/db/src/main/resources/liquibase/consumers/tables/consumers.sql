CREATE TABLE consumers (
	id BIGINT IDENTITY
	, documento     BIGINT        NOT NULL
	, nombre        VARCHAR(100)  NOT NULL
	, apellido      VARCHAR(100)  NOT NULL
	, nro_telefono  VARCHAR(20)
	, email         VARCHAR(50)
	, fecha_de_alta DATETIME      NOT NULL  DEFAULT SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
	, UNIQUE(documento)
	, PRIMARY KEY(id)
	, FOREIGN KEY(documento)      REFERENCES usuario(documento)
);