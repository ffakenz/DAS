CREATE TABLE config_param (
    config_tecnologica VARCHAR(100) NOT NULL
	, nombre VARCHAR(100) NOT NULL
	, PRIMARY KEY (config_tecnologica, nombre)
	, FOREIGN KEY (config_tecnologica) REFERENCES config_tecnologica(nombre)
);