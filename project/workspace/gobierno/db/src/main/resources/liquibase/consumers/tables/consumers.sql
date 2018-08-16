CREATE TABLE consumers (
	id              BIGINT        NOT NULL  IDENTITY
	, documento     BIGINT        NOT NULL
	, nombre        VARCHAR(100)  NOT NULL
	, apellido      VARCHAR(100)  NOT NULL
	, nro_telefono  VARCHAR(20)
	, email         VARCHAR(50)
	, fecha_de_alta DATETIME      NOT NULL  DEFAULT GETDATE()
	, concesionaria BIGINT        NOT NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY (concesionaria) REFERENCES concesionaria(id)
	, FOREIGN KEY (documento) REFERENCES usuario(documento)
	, UNIQUE(documento, concesionaria)
);