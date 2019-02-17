CREATE TABLE concesionaria (
	id BIGINT IDENTITY
	, nombre VARCHAR(100) NOT NULL
	, fecha_registracion DATETIME NOT NULL DEFAULT SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
	, fecha_alta DATETIME NULL
	, codigo VARCHAR(50) NULL
	, direccion VARCHAR(100) NOT NULL
	, cuit VARCHAR(50) NOT NULL
	, tel VARCHAR(50) NOT NULL
	, email VARCHAR(50) NOT NULL
	, PRIMARY KEY(id)
	, UNIQUE(cuit)
);