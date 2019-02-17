CREATE TABLE vehiculos (
	id                  BIGINT                      IDENTITY
	, tipo              VARCHAR(100)    NOT NULL
	, nombre            VARCHAR(100)    NOT NULL
	, marca             VARCHAR(100)    NOT NULL
	, fecha_de_alta     DATETIME        NOT NULL    DEFAULT SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
	, precio            BIGINT          NOT NULL
	, color             VARCHAR(100)    NOT NULL
	, modelo            VARCHAR(100)    NOT NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY(tipo) REFERENCES tipos_vehiculo(nombre)
	, UNIQUE(tipo, nombre)
);
