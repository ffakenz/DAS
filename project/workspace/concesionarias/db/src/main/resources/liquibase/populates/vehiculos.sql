CREATE TABLE vehiculos (
	id INT IDENTITY PRIMARY KEY
	, tipo VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES tipos_vehiculo(nombre)
	, nombre VARCHAR(100) NOT NULL
	, marca VARCHAR(100) NOT NULL
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, precio BIGINT NOT NULL
	, color VARCHAR(100) NOT NULL
	, modelo VARCHAR(100) NOT NULL
	, UNIQUE(tipo, nombre)
);