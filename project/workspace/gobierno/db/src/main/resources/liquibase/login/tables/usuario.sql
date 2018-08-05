CREATE TABLE usuario (
	username VARCHAR(100) NOT NULL
	, password VARCHAR(100) NOT NULL
	, rol VARCHAR(100) NOT NULL
	, PRIMARY KEY(username)
	, FOREIGN KEY(rol) REFERENCES tipo_usuario(nombre)
);