CREATE PROCEDURE get_concesionaria_x_config_tecnologica(
    @concesionariaId BIGINT
    , @configTecnologica VARCHAR(100)
) AS
SELECT *
FROM concesionaria_x_config_tecnologica
WHERE concesionaria_id = @concesionariaId
    AND config_tecnologica = @configTecnologica;