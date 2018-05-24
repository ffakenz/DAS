CREATE TABLE detalle_usuario (
	username VARCHAR(100) NOT NULL
	, documento BIGINT NOT NULL
	, nombre VARCHAR(100) NOT NULL
	, PRIMARY KEY(username)
	, FOREIGN KEY(username) REFERENCES usuario(username)
);