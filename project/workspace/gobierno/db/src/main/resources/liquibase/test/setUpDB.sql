CREATE PROCEDURE setUpDB AS
BEGIN

    INSERT INTO tipos_vehiculo(nombre)
    VALUES  ('taxi')
    	    , ('particular')
    	    , ('comercial')
    	    , ('camion')
    	    , ('utilitario')

    INSERT INTO vehiculos(tipo, nombre, precio, marca, modelo, color)
    VALUES  ('taxi', 'Corsa', 10000, 'chevrolet', 'v1','c1' )
    	    , ('particular', 'Gol', 10000, 'volkswagen', 'v3','c2' )
    	    , ('particular', 'Clio', 10000, 'renault', 'v1','c1' )
    	    , ('comercial', '208', 10000, 'peugeot', 'v1','c4' )
    	    , ('comercial', 'Focus', 10000, 'ford', 'v5','c3' )
    	    , ('utilitario', 'Fiorino', 10000, 'fiat', 'v1','c1' )
    	    , ('utilitario', 'Saveiro', 10000, 'volkswagen', 'v2','c1' )

    INSERT INTO config_tecnologica(nombre)
    VALUES  ('REST')
          , ('CXF')
          , ('AXIS')

    INSERT INTO config_param (config_tecnologica, nombre)
        VALUES ('AXIS', 'endpointUrl')
             , ('AXIS', 'targetNameSpace')
             , ('CXF', 'wsdlUrl')
             , ('REST', 'url')

    INSERT INTO concesionaria(nombre, direccion, cuit, tel, email)
    VALUES ('C1', 'La Tablada 5739', '21-93337511-1', '+5493513059161', 'c1@gmail.com')
         , ('C2', 'La Tablada2 5739', '22-93337511-2', '+5493513059162', 'c2@gmail.com')
         , ('C3', 'La Tablada3 5739', '22-93337511-3', '+5493513059163', 'c3@gmail.com')
         , ('C4', 'La Tablada4 5739', '23-93337511-4', '+5493513059164', 'c4@gmail.com')
         , ('C5', 'La Tablada5 5739', '24-93337511-5', '+5493513059165', 'c5@gmail.com')

    INSERT INTO concesionaria_x_config_tecnologica(concesionaria_id, config_tecnologica)
    VALUES (1, 'REST')
         , (2, 'CXF')
         , (3, 'AXIS')
         , (4, 'REST')
         , (5, 'CXF')

    INSERT INTO concesionaria_config_params (concesionaria_id, config_tecno, config_param, value)
    VALUES
        (1, 'REST', 'url', 'http://localhost:8001/concesionaria_rest_one/concesionariaRestOne')
        , (2, 'CXF', 'wsdlUrl', 'http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl')
        , (3, 'AXIS', 'endpointUrl', 'http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/')
        , (3, 'AXIS', 'targetNameSpace', 'http://ws.ConcesionariaAxisOne/')
        , (4, 'REST', 'url', 'http://localhost:8004/concesionarias_rest_four/concesionariaRestFour')
        , (5, 'CXF', 'wsdlUrl', 'http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl')

    INSERT INTO tipo_usuario(nombre)
    VALUES
        ('consumer')
        ,('gobierno')

    INSERT INTO usuario(documento, username, password, rol)
    VALUES (111, 'irocca' , 'lam'  , 'gobierno')
	       , (222, 'ffakenz', '123'  , 'gobierno')
         , (1, 'pepe'   , 'perez', 'consumer')
         , (2, 'ricky'  , 'fort' , 'consumer')
         , (3,  null    ,  null  , 'consumer')
         , (4,  null    ,  null  , 'consumer')

    INSERT INTO consumers
        (documento, nombre, apellido, nro_telefono, email)
    VALUES (1, 'Pepe' , 'Perez' , '35156345678', 'carliperezozo@mail.com')
         , (2, 'Ricky', 'Fort'  , '35156345675', 'rickyfort@mail.com'    )
         , (3, 'Steve', 'Jobs'  , '35156345675', 'stevethebest@mail.com' )
        --, (444, 'Franco', 'Maradona', '35156345671', 'FrancoMaradona@mail.com')
        --, (555, 'Walter', 'Quejeiro', '35156345676', 'walterquejeiro@mail.com')
        --, (666, 'Pamela', 'Anderson', '35156345677', 'pamelaanderson@mail.com')

    INSERT INTO tipos_estado_cuentas (tipo)
        VALUES ('inicial')
            ,('en_proceso')
            ,('pagado')
            ,('cancelado')

    INSERT INTO estado_cuentas
      ( concesionaria, nro_plan_concesionaria, dni_consumer, vehiculo, fecha_alta_concesionaria, estado)
    VALUES
        -- basic
        (1, 1001, 1,  1, '2018-01-01 21:58:01', 'en_proceso')
        -- same client in 2 different concesionarias
        , (1, 1002, 2, 2, '2018-01-01 21:58:01', 'en_proceso')
        , (2, 1003, 2, 3, '2018-02-02 22:58:02', 'en_proceso')
        -- same concesionaria, same cliente, 2 different planes
        , (3, 1004, 3, 1, '2018-03-01 21:58:01', 'en_proceso')
        , (3, 1005, 3, 2, '2018-03-02 22:58:02', 'en_proceso')

    INSERT INTO login(documento, log_out_time)
    VALUES (111, GETDATE())
    	   , (222, default)

    INSERT INTO cuotas (
        nro_cuota, estado_cuenta_id, fecha_alta_concesionaria, fecha_vencimiento, monto, fecha_pago
    )
    VALUES
        -- plan al dia
       (1, 1, '2018-01-01 21:58:01', '2018-02-01 21:58:01', 10000, '2018-01-27 21:58:01')
       , (2, 1, '2018-02-01 21:58:01', '2018-03-01 21:58:01', 10000, '2018-02-11 21:58:01')
       , (3, 1, '2018-03-01 21:58:01', '2018-04-01 21:58:01', 10000, '2018-03-31 21:58:01')
       -- plan vencido
       , (1, 2, '2018-01-01 21:58:01', '2018-02-01 21:58:01', 10000, '2018-02-02 21:58:01')
       , (2, 2, '2018-02-01 21:58:01', '2018-03-01 21:58:01', 10000, '2018-02-27 21:58:01')
       , (3, 2, '2018-03-01 21:58:01', '2018-04-01 21:58:01', NULL, NULL)
       -- plan nuevo
       -- otros al dia
       , (1, 3, '2018-03-01 21:58:01', '2018-04-01 21:58:01', 10000, '2018-03-27 21:58:01')
       , (2, 3, '2018-04-01 21:58:01', '2018-05-01 21:58:01', 10000, '2018-04-11 21:58:01')
       , (3, 3, '2018-05-01 21:58:01', '2018-06-01 21:58:01', 10000, '2018-05-30 21:58:01')
       , (1, 4, '2018-03-01 21:58:01', '2018-04-01 21:58:01', 10000, '2018-03-27 21:58:01')
       , (2, 4, '2018-04-01 21:58:01', '2018-05-01 21:58:01', 10000, '2018-04-11 21:58:01')
       , (3, 4, '2018-05-01 21:58:01', '2018-06-01 21:58:01', 10000, '2018-05-30 21:58:01')


    -- SORTEO
    INSERT INTO estado_sorteo(nombre)
    VALUES('nuevo'),('pendiente'),('completado')

    INSERT INTO sorteos (mes_sorteo, estado, fecha_ejecucion)
    VALUES(10, 'completado', GETDATE())

    INSERT INTO estado_participante(nombre)
    VALUES('participante'),('ganador'),('cancelado')

    INSERT INTO participantes(id_sorteo, id_plan, estado)
    VALUES (1, 4, 'ganador')

    -- CONSUMO
    INSERT INTO estado_consumo(estado)
    VALUES ('success')
            , ('failure')

    INSERT INTO tipo_consumo_result(tipo)
    VALUES ('success')
            , ('failure')


END
GO