CREATE TABLE concesionaria (
	id BIGINT IDENTITY
	, nombre VARCHAR(100) NOT NULL
	, config VARCHAR(100) NOT NULL
	, fecha_registracion DATETIME NOT NULL DEFAULT GETDATE()
	, fecha_alta DATETIME NULL
	, codigo VARCHAR(10) NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY(config) REFERENCES config_tecnologica(nombre)
	, UNIQUE(nombre, config)
);