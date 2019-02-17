CREATE TABLE consumer_x_concesionaria (
	id              BIGINT IDENTITY
	, consumer      BIGINT        NOT NULL
	, concesionaria BIGINT        NOT NULL
	, fecha_de_alta DATETIME      NOT NULL  DEFAULT SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
	, PRIMARY KEY(id)
	, FOREIGN KEY(consumer)         REFERENCES consumers(id)
	, FOREIGN KEY(concesionaria)    REFERENCES concesionaria(id)
);