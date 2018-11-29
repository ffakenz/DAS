CREATE TABLE clientes (
	id_cliente BIGINT IDENTITY NOT NULL
	, documento BIGINT NOT NULL
	, nombre VARCHAR(100) NOT NULL
	, apellido VARCHAR(100) NOT NULL
	, nro_telefono VARCHAR(20)
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, PRIMARY KEY(id_cliente) -- unico por consecionaria
);