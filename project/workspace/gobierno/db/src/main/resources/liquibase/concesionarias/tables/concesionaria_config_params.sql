CREATE TABLE concesionaria_config_params (
	concesionaria_id BIGINT NOT NULL
	, config_tecno VARCHAR(100) NOT NULL
	, config_param VARCHAR(100) NOT NULL
	, value VARCHAR(500) NOT NULL
	, PRIMARY KEY(concesionaria_id, config_tecno, config_param)
	, FOREIGN KEY(config_tecno, concesionaria_id) REFERENCES concesionaria(config, id)
	, FOREIGN KEY(config_tecno, config_param) REFERENCES config_param(config_tecnologica, nombre)
);