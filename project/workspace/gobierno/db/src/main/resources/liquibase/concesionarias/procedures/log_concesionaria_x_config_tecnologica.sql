CREATE PROCEDURE log_concesionaria_x_config_tecnologica(
    @concesionariaId BIGINT
    , @configTecnologica VARCHAR(100)
) AS
INSERT INTO concesionaria_x_config_tecnologica(concesionaria_id, config_tecnologica)
VALUES (@concesionariaId, @configTecnologica)