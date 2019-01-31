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
       (1, 1001, 333, 1, '2018-01-01 21:58:01', 'en_proceso')
    -- same client in 2 different concesionarias
    , (1, 1002, 444, 2, '2018-01-01 21:58:01', 'en_proceso')
    , (2, 1003, 444, 3, '2018-02-02 22:58:02', 'en_proceso')
    -- same concesionaria, same cliente, 2 different planes
    , (3, 1004, 555, 1, '2018-03-01 21:58:01', 'en_proceso')
    , (3, 1005, 555, 2, '2018-03-02 22:58:02', 'en_proceso')