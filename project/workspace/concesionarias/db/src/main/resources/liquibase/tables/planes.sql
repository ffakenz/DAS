CREATE TABLE planes (
	id BIGINT IDENTITY
	, cliente BIGINT NOT NULL
	, vehiculo BIGINT FOREIGN KEY REFERENCES vehiculos(id)
	, tipo_de_plan VARCHAR(100) NOT NULL DEFAULT 'NORMAL'
	, cant_cuotas_pagas INT NOT NULL DEFAULT 0 -- se actualiza por cada actualizacion
	, nombre VARCHAR(100) NOT NULL
	, fecha_alta DATETIME NOT NULL -- informado por la consecionaria
	, fecha_ultima_actualizacion DATETIME NOT NULL DEFAULT GETDATE() -- se actualiza por cada actualizacion
	, PRIMARY KEY(id)
	, FOREIGN KEY(cliente) REFERENCES clientes(id_cliente)
	, FOREIGN KEY(tipo_de_plan) REFERENCES tipos_de_planes(nombre)
	, UNIQUE(vehiculo, cliente, fecha_alta)
);