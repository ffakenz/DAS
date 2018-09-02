CREATE PROCEDURE setUpDB AS
BEGIN
    INSERT INTO config_tecnologica(nombre)
    VALUES
        ('REST')
        , ('CXF')
        , ('AXIS')

    INSERT INTO concesionaria(nombre, config, direccion, cuit, tel, email)
    VALUES
        ('C1', 'REST', 'La Tablada 5739', '21-93337511-1', '+5493513059161', 'c1@gmail.com')
        , ('C2', 'CXF', 'La Tablada2 5739', '22-93337511-2', '+5493513059162', 'c2@gmail.com')
        , ('C3', 'AXIS', 'La Tablada3 5739', '22-93337511-3', '+5493513059163', 'c3@gmail.com')
        , ('C4', 'REST', 'La Tablada4 5739', '23-93337511-4', '+5493513059164', 'c4@gmail.com')
        , ('C5', 'CXF', 'La Tablada5 5739', '24-93337511-5', '+5493513059165', 'c5@gmail.com')

    INSERT INTO config_param (config_tecnologica, nombre)
    VALUES
        ('AXIS', 'endpointUrl')
        , ('AXIS', 'targetNameSpace')
        , ('CXF', 'wsdlUrl')
        , ('REST', 'url')

    INSERT INTO concesionaria_config_params (concesionaria_id, config_tecno, config_param, value)
    VALUES
        (1, 'REST', 'url', 'http://localhost:8002/concesionarias_rest_one/concesionariaRestOne')
        , (2, 'CXF', 'wsdlUrl', 'http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl')
        , (3, 'AXIS', 'endpointUrl', 'http://192.168.1.6:8001/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/')
        , (3, 'AXIS', 'targetNameSpace', 'http://ws.ConcesionariaAxisOne/')
        , (5, 'CXF', 'wsdlUrl', 'http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl')

    INSERT INTO consumers
        (documento, nombre, apellido, nro_telefono, email, concesionaria)
    VALUES
        (111, 'Carlos', 'Perez', '35156345678', 'carliperezozo@mail.com', 1)
        , (222, 'Ricky', 'Fort', '35156345675', 'rickyfort@mail.com', 1)
        , (222, 'Ricky', 'Fort', '35156345679', 'rickyfort@mail.com', 2)
        , (333, 'Juan', 'Rocca', '35156345672', 'JuanRocca@mail.com', 3)
        , (444, 'Franco', 'Maradona', '35156345671', 'FrancoMaradona@mail.com', 3)
        , (555, 'Walter', 'Quejeiro', '35156345676', 'walterquejeiro@mail.com', 4)
        , (666, 'Pamela', 'Anderson', '35156345677', 'pamelaanderson@mail.com', 5)

    INSERT INTO tipo_usuario(nombre)
    VALUES
        ('consumer')
        ,('gobierno')

    INSERT INTO usuario(username, password, rol)
    VALUES
        ('irocca', 'lam', 'gobierno')
        , ('ffakenz', '123', 'gobierno')
        , ('pepe', 'asd', 'consumer')
        , ('pepe2', 'asd', 'consumer')

    INSERT INTO tipos_estado_cuentas (tipo)
        VALUES ('inicial')
            ,('en_proceso')
            ,('pagado')
            ,('cancelado')

    INSERT INTO login(username, log_out_time)
    VALUES
    	('irocca', GETDATE())
    	, ('ffakenz', default)

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

    INSERT INTO cuotas (
        estado_cuenta_id, fecha_vencimiento, monto, fecha_pago
    )
    VALUES
        -- plan al dia
       (1, '2018-02-01 21:58:01', 10000, '2018-01-27 21:58:01')
       , (1, '2018-03-01 21:58:01', 10000, '2018-02-11 21:58:01')
       , (1, '2018-04-01 21:58:01', 10000, '2018-03-31 21:58:01')
       -- plan vencido
       , (2, '2018-02-01 21:58:01', 10000, '2018-02-02 21:58:01')
       , (2, '2018-03-01 21:58:01', 10000, '2018-02-27 21:58:01')
       , (2, '2018-04-01 21:58:01', NULL, NULL)
       -- plan nuevo
       -- otros al dia
       , (3, '2018-04-01 21:58:01', 10000, '2018-03-27 21:58:01')
       , (3, '2018-05-01 21:58:01', 10000, '2018-04-11 21:58:01')
       , (3, '2018-06-01 21:58:01', 10000, '2018-05-30 21:58:01')
       , (4, '2018-04-01 21:58:01', 10000, '2018-03-27 21:58:01')
       , (4, '2018-05-01 21:58:01', 10000, '2018-04-11 21:58:01')
       , (4, '2018-06-01 21:58:01', 10000, '2018-05-30 21:58:01')

END
GO