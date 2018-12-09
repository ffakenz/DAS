CREATE PROCEDURE invalidate_params ( @concesionariaId BIGINT ) AS
UPDATE concesionaria_config_params
SET is_valid = 0
WHERE concesionaria_id = @concesionariaId;