CREATE PROCEDURE update_concesionaria_config_params (
    @concesionaria_id BIGINT
    , @config_tecno VARCHAR(100)
    , @config_param VARCHAR(100)
    , @value VARCHAR(500)
) AS
UPDATE concesionaria_config_params
SET value = @value
WHERE concesionaria_id = @concesionaria_id
  AND config_tecno = @config_tecno
  AND config_param = @config_param;