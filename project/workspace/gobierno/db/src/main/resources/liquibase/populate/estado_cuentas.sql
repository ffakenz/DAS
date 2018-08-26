INSERT INTO estado_cuentas (
    concesionaria
    , nro_plan_concesionaria
    , dni_consumer
    , vehiculo
    , fecha_alta_concesionaria
    , estado
)
VALUES
    -- basic
    (1, 1001, 111, 1, '2018-01-01 21:58:01', 'en_proceso')
    -- same client in 2 different concesionarias
    , (1, 1002, 222, 2, '2018-01-01 21:58:01', 'en_proceso')
    , (2, 1003, 222, 3, '2018-02-02 22:58:02', 'en_proceso')
    -- same concesionaria, same cliente, 2 different planes
    , (3, 1004, 333, 1, '2018-03-01 21:58:01', 'en_proceso')
    , (3, 1005, 333, 2, '2018-03-02 22:58:02', 'en_proceso')