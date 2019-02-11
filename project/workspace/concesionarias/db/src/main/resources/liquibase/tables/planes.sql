CREATE TABLE planes (
	id                              BIGINT                      IDENTITY
	, cliente                       BIGINT          NOT NULL
	, vehiculo                      BIGINT          NOT NULL
	, tipo_de_plan                  VARCHAR(100)    NOT NULL    DEFAULT 'NORMAL'
	, estado                        VARCHAR(100)    NOT NULL
	, fecha_alta                    DATETIME        NOT NULL -- informado por la consecionaria
	, fecha_ultima_actualizacion    DATETIME        NOT NULL -- informado por la consecionaria
	, PRIMARY KEY(id)
	, FOREIGN KEY(vehiculo)     REFERENCES vehiculos(id)
	, FOREIGN KEY(cliente)      REFERENCES clientes(id_cliente)
	, FOREIGN KEY(tipo_de_plan) REFERENCES tipos_de_planes(nombre)
	, FOREIGN KEY(estado)       REFERENCES tipo_estado_planes(nombre)
	, UNIQUE(vehiculo, cliente, fecha_alta)
);