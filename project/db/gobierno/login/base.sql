USE db_gobierno;

DROP TABLE login;
DROP TABLE detalle_usuario;
DROP TABLE usuario;
DROP TABLE tipo_usuario;

CREATE TABLE tipo_usuario (
	nombre VARCHAR(100)
	, PRIMARY KEY(nombre)
);

CREATE TABLE usuario (
	username VARCHAR(100) NOT NULL
	, password VARCHAR(100) NOT NULL
	, rol VARCHAR(100) NOT NULL
	, PRIMARY KEY(username)
	, FOREIGN KEY(rol) REFERENCES tipo_usuario(nombre)
	, CHECK(rol IN ('comprador', 'gobierno'))
);



CREATE TABLE detalle_usuario (
	username VARCHAR(100) NOT NULL
	, documento BIGINT NOT NULL
	, nombre VARCHAR(100) NOT NULL
	, PRIMARY KEY(username)
	, FOREIGN KEY(username) REFERENCES usuario(username)
);

CREATE TABLE login (
	id BIGINT IDENTITY NOT NULL 
	, username VARCHAR(100) NOT NULL
	, loginTime DATETIME NOT NULL DEFAULT GETDATE()
	, logoutTime DATETIME DEFAULT NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY(username) REFERENCES usuario(username)
);