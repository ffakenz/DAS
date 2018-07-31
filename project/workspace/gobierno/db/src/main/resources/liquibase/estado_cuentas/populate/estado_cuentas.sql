INSERT INTO estado_cuentas (
    concesionaria
    , nro_plan_concesionaria
    , documento_cliente
    , vehiculo
    , fecha_alta_concesionaria
    , estado
)
VALUES
    -- basic
    (1, 1, 93337511, 1, '2018-01-01 21:58:01', 'en_proceso')
    -- same client in 2 different concesionarias
    , (1, 2, 93337512, 2, '2018-01-01 21:58:01', 'en_proceso')
    , (2, 1, 93337512, 3, '2018-02-02 22:58:02', 'en_proceso')
    -- same concesionaria, same cliente, 2 different planes
    , (3, 1, 9333753, 1, '2018-03-01 21:58:01', 'en_proceso')
    , (3, 2, 9333753, 2, '2018-03-02 22:58:02', 'en_proceso')