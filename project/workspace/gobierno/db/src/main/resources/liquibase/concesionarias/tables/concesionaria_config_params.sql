CREATE TABLE concesionaria_config_params (
	id BIGINT IDENTITY
	, concesionaria_id BIGINT NOT NULL
	, config_tecno VARCHAR(100) NOT NULL
	, config_param VARCHAR(100) NOT NULL
	, value VARCHAR(500) NOT NULL
	, is_valid BIT NOT NULL DEFAULT 1 -- every new config_para is valid
	, PRIMARY KEY(id)
	, FOREIGN KEY(concesionaria_id, config_tecno) REFERENCES concesionaria_x_config_tecnologica(concesionaria_id, config_tecnologica)
	, FOREIGN KEY(config_tecno, config_param) REFERENCES config_param(config_tecnologica, nombre)
);