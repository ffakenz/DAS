CREATE PROCEDURE log_estado_cuentas (
    @concesionaria BIGINT
    , @nro_plan_concesionaria BIGINT
    , @dni_consumer BIGINT
    , @vehiculo BIGINT
    , @fecha_alta_concesionaria DATETIME
    , @estado VARCHAR(30)
) AS
INSERT INTO estado_cuentas(concesionaria
                       , nro_plan_concesionaria
                       , dni_consumer
                       , vehiculo
                       , fecha_alta_concesionaria
                       , estado)
VALUES (@concesionaria
        , @nro_plan_concesionaria
        , @dni_consumer
        , @vehiculo
        , @fecha_alta_concesionaria
        , @estado);