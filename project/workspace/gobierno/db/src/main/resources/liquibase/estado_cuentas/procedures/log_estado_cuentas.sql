CREATE PROCEDURE log_estado_cuentas (
    @concesionaria BIGINT
    , @nro_plan_concesionaria BIGINT
    , @documento_cliente BIGINT
    , @vehiculo BIGINT
    , @fecha_alta_concesionaria DATETIME
    , @estado VARCHAR(30)
) AS
INSERT INTO estado_cuentas(concesionaria
                       , nro_plan_concesionaria
                       , documento_cliente
                       , vehiculo
                       , fecha_alta_concesionaria
                       , estado)
VALUES (@concesionaria
        , @nro_plan_concesionaria
        , @documento_cliente
        , @vehiculo
        , @fecha_alta_concesionaria
        , @estado);