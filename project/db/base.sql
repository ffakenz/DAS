DROP TABLE ganadores;
DROP TABLE estado_ganador;
DROP TABLE consecionarias_notificadas;
DROP TABLE sorteos;
DROP TABLE estado_sorteo;
DROP TABLE planes;
DROP TABLE clientes;
DROP TABLE consecionarias;
DROP TABLE config_tecnologicas;
DROP TABLE vehiculos;
DROP TABLE tipos_vehiculo;
DROP TABLE usuarios;
DROP TABLE roles;

CREATE TABLE roles (
	nombre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE usuarios (
	documento BIGINT NOT NULL PRIMARY KEY
	, nombre VARCHAR(100) NOT NULL
	, password VARCHAR(100) NOT NULL
	, rol VARCHAR(100) FOREIGN KEY REFERENCES roles(nombre)
);

CREATE TABLE tipos_vehiculo (
	nombre VARCHAR(100) PRIMARY KEY 
);

CREATE TABLE vehiculos (
	id INT IDENTITY PRIMARY KEY
	, tipo VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES tipos_vehiculo(nombre)
	, nombre VARCHAR(100) NOT NULL
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, UNIQUE(tipo, nombre)
);

CREATE TABLE config_tecnologicas (
	nombre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE consecionarias (
	id INT IDENTITY PRIMARY KEY
	, nombre VARCHAR(100) NOT NULL
	, config VARCHAR(100) FOREIGN KEY REFERENCES config_tecnologicas(nombre)
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, UNIQUE(nombre, config)
);

CREATE TABLE clientes (
	id_consecionaria INT FOREIGN KEY REFERENCES consecionarias(id)
	, correlativo INT NOT NULL -- informado por la consecionaria
	, documento BIGINT NOT NULL
	, id AS CAST(id_consecionaria AS VARCHAR(50)) + '-' + CAST(correlativo AS VARCHAR(50))
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, PRIMARY KEY(correlativo, id_consecionaria) -- unico por consecionaria
);



CREATE TABLE planes (
	id INT IDENTITY PRIMARY KEY
	, consecionaria INT FOREIGN KEY REFERENCES consecionarias(id)
	, cliente INT NOT NULL 
	, vehiculo INT FOREIGN KEY REFERENCES vehiculos(id)
	, cant_cuotas_pagas INT NOT NULL DEFAULT 0 -- se actualiza por cada actualizacion
	, fecha_alta DATETIME NOT NULL -- informado por la consecionaria
	, fecha_ultima_actualizacion DATETIME NOT NULL DEFAULT GETDATE() -- se actualiza por cada actualizacion
	, FOREIGN KEY(cliente, consecionaria) REFERENCES clientes(correlativo, id_consecionaria)
	, UNIQUE(consecionaria, vehiculo, cliente, fecha_alta)
);


CREATE TABLE estado_sorteo (
	nombre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE sorteos (
	id INT IDENTITY PRIMARY KEY
	, mes_sorteo INT DEFAULT DATEPART(MONTH, GETDATE())
	, estado VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES estado_sorteo(nombre) DEFAULT 'nuevo'
	, fecha DATE NULL
	, UNIQUE(fecha)
);

CREATE TABLE estado_ganador (
	nombre VARCHAR(100) PRIMARY KEY
);


CREATE TABLE ganadores (
	id INT IDENTITY PRIMARY KEY
	, sorteo_id INT NOT NULL FOREIGN KEY REFERENCES sorteos(id)
	, plan_id INT NOT NULL FOREIGN KEY REFERENCES planes(id)
	, estado VARCHAR(100) NOT NULL FOREIGN KEY REFERENCES estado_ganador(nombre) DEFAULT 'pendiente'
);

CREATE TABLE consecionarias_notificadas (
	sorteo INT NOT NULL FOREIGN KEY REFERENCES sorteos(id) PRIMARY KEY
	, consecionaria INT NOT NULL  FOREIGN KEY REFERENCES consecionarias(id)
	, descripcion VARCHAR(100) NOT NULL  DEFAULT 'Consecionaria Auditada'
);


