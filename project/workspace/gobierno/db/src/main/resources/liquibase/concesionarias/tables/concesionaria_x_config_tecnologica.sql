CREATE TABLE concesionaria_x_config_tecnologica (
	concesionaria_id BIGINT NOT NULL
	, config_tecnologica VARCHAR(100) NOT NULL
	, PRIMARY KEY(concesionaria_id, config_tecnologica)
	, FOREIGN KEY(concesionaria_id) REFERENCES concesionaria(id)
	, FOREIGN KEY(config_tecnologica) REFERENCES config_tecnologica(nombre)
);