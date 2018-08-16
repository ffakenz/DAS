CREATE TABLE usuario (
	documento BIGINT NOT NULL
	, username VARCHAR(100)
	, password VARCHAR(100)
	, rol VARCHAR(100) NOT NULL
	, PRIMARY KEY (documento)
	, UNIQUE(username)
	, FOREIGN KEY (rol) REFERENCES tipo_usuario(nombre)
);