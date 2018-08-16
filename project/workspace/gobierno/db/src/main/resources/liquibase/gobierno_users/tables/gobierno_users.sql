CREATE TABLE gobierno_users (
	documento BIGINT NOT NULL PRIMARY KEY
	, nombre VARCHAR(100) NOT NULL
	, apellido VARCHAR(100) NOT NULL
	, nro_telefono VARCHAR(20)
	, email VARCHAR(50)
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, FOREIGN KEY(documento) REFERENCES usuario(documento)
);