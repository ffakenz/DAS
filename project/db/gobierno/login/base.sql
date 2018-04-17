USE db_gobierno;

DROP TABLE login;
DROP TABLE detalle_usuario;
DROP TABLE usuario;
DROP TABLE tipo_usuario;

CREATE TABLE tipo_usuario (
	nombre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE usuario (
	tipo VARCHAR(100) FOREIGN KEY REFERENCES tipo_usuario(nombre)
	, username VARCHAR(100) NOT NULL
	, password VARCHAR(100) NOT NULL
	, PRIMARY KEY(tipo, username)
);

CREATE TABLE detalle_usuario (
	tipo VARCHAR(100) NOT NULL
	, username VARCHAR(100) NOT NULL
	, documento BIGINT NOT NULL
	, nombre VARCHAR(100) NOT NULL
	, PRIMARY KEY(tipo, username)
	, FOREIGN KEY(tipo, username) REFERENCES usuario(tipo, username)
);

CREATE TABLE login (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY
	, tipo VARCHAR(100) 
	, username VARCHAR(100) NOT NULL
	, loginTime DATETIME NOT NULL DEFAULT GETDATE()
	, logoutTime DATETIME DEFAULT NULL
	, FOREIGN KEY(tipo, username) REFERENCES usuario(tipo, username)
);