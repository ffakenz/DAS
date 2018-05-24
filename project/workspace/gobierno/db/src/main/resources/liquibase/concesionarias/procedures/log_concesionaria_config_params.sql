CREATE PROCEDURE log_concesionaria_config_params(@concesionaria_id BIGINT, @config_tecno VARCHAR(100), @config_param VARCHAR(100), @value VARCHAR(500)) AS
INSERT INTO concesionaria_config_params(concesionaria_id, config_tecno, config_param, value)
VALUES (@concesionaria_id, @config_tecno, @config_param, @value);