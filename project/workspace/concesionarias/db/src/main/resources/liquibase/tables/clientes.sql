CREATE TABLE clientes (
	id_cliente      BIGINT          NOT NULL    IDENTITY
	, documento     BIGINT          NOT NULL
	, nombre        VARCHAR(100)    NOT NULL
	, apellido      VARCHAR(100)    NOT NULL
	, nro_telefono  VARCHAR(20)
	, email         VARCHAR(50)
	, fecha_de_alta DATETIME        NOT NULL
	, PRIMARY KEY(id_cliente) -- unico por consecionaria
	, UNIQUE(documento)
);