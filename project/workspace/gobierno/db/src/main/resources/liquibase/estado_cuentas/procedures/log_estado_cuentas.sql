CREATE PROCEDURE log_estado_cuentas (
    @id BIGINT
    , @concesionaria BIGINT
    , @nro_plan_concesionaria BIGINT
    , @documento_cliente BIGINT
    , @vehiculo BIGINT
    , @fecha_alta_concesionaria DATETIME
    , @fecha_alta_sistema DATETIME
    , @fecha_ultima_actualizacion DATETIME
) AS
INSERT INTO estado_cuentas(id BIGINT
                       , concesionaria BIGINT
                       , nro_plan_concesionaria BIGINT
                       , documento_cliente BIGINT
                       , vehiculo BIGINT
                       , fecha_alta_concesionaria DATETIME
                       , fecha_alta_sistema DATETIME
                       , fecha_ultima_actualizacion DATETIME)
VALUES (@id BIGINT
            , @concesionaria BIGINT
            , @nro_plan_concesionaria BIGINT
            , @documento_cliente BIGINT
            , @vehiculo BIGINT
            , @fecha_alta_concesionaria DATETIME
            , @fecha_alta_sistema DATETIME
            , @fecha_ultima_actualizacion DATETIME);