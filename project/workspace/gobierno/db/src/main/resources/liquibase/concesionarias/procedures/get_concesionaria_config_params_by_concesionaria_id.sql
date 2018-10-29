CREATE PROCEDURE get_concesionaria_config_params_by_concesionaria_id(
    @concesionaria_id BIGINT
) AS
SELECT *
FROM concesionaria_config_params
WHERE concesionaria_id = @concesionaria_id
    AND is_valid = 1;