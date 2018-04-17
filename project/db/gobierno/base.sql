USE db_gobierno;
GO
DROP TABLE ganadores;
DROP TABLE estado_ganador;
DROP TABLE concesionarias_notificadas;
DROP TABLE participantes;
DROP TABLE sorteos;
DROP TABLE estado_sorteo;
DROP TABLE detalle_cuotas
DROP TABLE planes;
DROP TABLE clientes;
DROP TABLE concesionarias;
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
	, marca VARCHAR(100) NOT NULL
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, precio BIGINT NOT NULL 
	, color VARCHAR(100) NOT NULL
	, modelo VARCHAR(100) NOT NULL
	, UNIQUE(tipo, nombre, marca, modelo, color, precio)
);

CREATE TABLE config_tecnologicas (
	nombre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE concesionarias (
	id INT IDENTITY PRIMARY KEY
	, nombre VARCHAR(100) NOT NULL
	, config VARCHAR(100) FOREIGN KEY REFERENCES config_tecnologicas(nombre)
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, UNIQUE(nombre, config)
);

CREATE TABLE clientes (
	id_concesionaria INT FOREIGN KEY REFERENCES concesionarias(id)
	, correlativo INT NOT NULL -- informado por la concesionaria
	, documento BIGINT NOT NULL
	, id AS CAST(id_concesionaria AS VARCHAR(50)) + '-' + CAST(correlativo AS VARCHAR(50))
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, nombre VARCHAR(50) NOT NULL
	, apellido VARCHAR(50) NOT NULL
	, PRIMARY KEY(correlativo, id_concesionaria) -- unico por concesionaria
	, UNIQUE(id)
);

CREATE TABLE planes (
	id INT IDENTITY PRIMARY KEY
	, concesionaria INT FOREIGN KEY REFERENCES concesionarias(id)
	, cliente INT NOT NULL 
	, tipo_vehiculo VARCHAR(100) NOT NULL
	, nombre_vehiculo VARCHAR(100) NOT NULL
	, marca_vehiculo VARCHAR(100) NOT NULL
	, modelo_vehiculo VARCHAR(100) NOT NULL 
	, color_vehiculo VARCHAR(100) NOT NULL
	, precio_vehiculo BIGINT NOT NULL
	, fecha_de_alta DATETIME NOT NULL DEFAULT GETDATE()
	, cant_cuotas_pagas INT NOT NULL DEFAULT 0 -- se actualiza por cada actualizacion
	, fecha_alta DATETIME NOT NULL -- informado por la concesionaria
	, fecha_ultima_actualizacion DATETIME NOT NULL DEFAULT GETDATE() -- se actualiza por cada actualizacion
	, estado VARCHAR(30) NOT NULL
	, FOREIGN KEY(cliente, concesionaria) REFERENCES clientes(correlativo, id_concesionaria)
	, FOREIGN KEY(tipo_vehiculo, nombre_vehiculo, marca_vehiculo, modelo_vehiculo, color_vehiculo, precio_vehiculo) REFERENCES vehiculos(tipo, nombre, marca, modelo, color, precio)
	, UNIQUE(concesionaria, cliente, fecha_alta)
);

CREATE TABLE detalle_cuotas (
	id_plan INT NOT NULL FOREIGN KEY REFERENCES planes(id)
	, id_cuota INT NOT NULL
	, estado VARCHAR(30) NOT NULL
	, fecha_pago DATETIME NULL
	, fecha_vencimiento DATETIME NOT NULL
	, PRIMARY KEY (id_plan, id_cuota)
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

CREATE TABLE participantes (
	id_sorteo INT NOT NULL FOREIGN KEY REFERENCES sorteos(id) 
	, id_cliente INT NOT NULL
	, id_concesionaria INT NOT NULL 
	, PRIMARY KEY (id_sorteo, id_cliente, id_concesionaria)
	, FOREIGN KEY (id_cliente, id_concesionaria) REFERENCES clientes(correlativo, id_concesionaria)
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

CREATE TABLE concesionarias_notificadas (
	sorteo INT NOT NULL FOREIGN KEY REFERENCES sorteos(id)
	, concesionaria INT NOT NULL  FOREIGN KEY REFERENCES concesionarias(id)
	, descripcion VARCHAR(100) NOT NULL  DEFAULT 'Concesionaria Auditada'
	, PRIMARY KEY (sorteo, concesionaria)
);

CREATE TABLE concesionarias_x_sorteo_que_informaron (
	sorteo INT NOT NULL FOREIGN KEY REFERENCES sorteos(id)
	, concesionaria INT NOT NULL  FOREIGN KEY REFERENCES concesionarias(id)
	, fecha_actualizacion DATETIME NOT NULL
	, PRIMARY KEY (sorteo, concesionaria)
);