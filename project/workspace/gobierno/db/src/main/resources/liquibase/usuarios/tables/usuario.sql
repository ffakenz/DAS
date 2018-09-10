CREATE TABLE usuario (
	username VARCHAR(100)
	, password VARCHAR(100)
	, rol VARCHAR(100) NOT NULL
	, PRIMARY KEY (username)
	, FOREIGN KEY (rol) REFERENCES tipo_usuario(nombre)
);