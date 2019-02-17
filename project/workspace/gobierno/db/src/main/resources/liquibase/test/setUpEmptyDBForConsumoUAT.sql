-- USED TO SUPPORT ConsumerJobSpec
CREATE PROCEDURE setUpEmptyDBForConsumoUAT AS
BEGIN

    INSERT INTO tipos_vehiculo(nombre)
    VALUES ('taxi')
         , ('particular')
         , ('comercial')
         , ('camion')
         , ('utilitario')

    INSERT INTO vehiculos(tipo, nombre, precio, marca, modelo, color)
    VALUES ('taxi', 'Corsa', 300000, 'chevrolet', 'v1','c1' )
         , ('particular', 'Gol', 300000, 'volkswagen', 'v3','c2' )
         , ('particular', 'Clio', 300000, 'renault', 'v1','c1' )
         , ('comercial', '208', 400000, 'peugeot', 'v1','c4' )
         , ('comercial', 'Focus', 600000, 'ford', 'v5','c3' )
         , ('utilitario', 'Fiorino', 600000, 'fiat', 'v1','c1' )
         , ('utilitario', 'Saveiro', 600000, 'volkswagen', 'v2','c1' )

    INSERT INTO config_tecnologica(nombre)
    VALUES ('REST')
         , ('CXF')
         , ('AXIS')

    INSERT INTO config_param (config_tecnologica, nombre)
    VALUES ('AXIS', 'endpointUrl')
         , ('AXIS', 'targetNameSpace')
         , ('CXF', 'wsdlUrl')
         , ('REST', 'url')

    INSERT INTO tipo_usuario(nombre)
    VALUES ('consumer')
         , ('gobierno')

    INSERT INTO tipos_estado_cuentas (tipo)
    VALUES ('en_proceso')
         , ('pagado')
         , ('cancelado')

    INSERT INTO estado_sorteo(nombre)
    VALUES ('nuevo')
         , ('pendiente_consumo')
         , ('pendiente_cancelacion')
         , ('pendiente_seleccion_ganador')
         , ('pendiente_notificacion_ganador')
         , ('pendiente_notificacion_concesionarias')
         , ('completado')
         , ('fallado')

    INSERT INTO estado_participante(nombre)
    VALUES ('participante')
         , ('ganador')
         , ('cancelado')
         , ('pendiente_cancelacion')

    INSERT INTO estado_consumo(estado)
    VALUES ('success')
         , ('failure')

    INSERT INTO tipo_consumo_result(tipo)
    VALUES ('success')
         , ('failure')

    INSERT INTO usuario(documento, username, password, rol)
    VALUES (111, 'irocca' , 'lam'  , 'gobierno')

    INSERT INTO concesionaria(nombre, direccion, cuit, tel, email, fecha_alta, codigo)
    VALUES ('Montironi', 'La Tablada 5739', '21-93337511-1', '+5493513059161', 'c1@gmail.com', SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time', 'CODIGO_C1')
         , ('Tagle', 'La Tablada2 5739', '22-93337511-2', '+5493513059162', 'c2@gmail.com', SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time', 'CODIGO_C2')
         , ('Avant', 'La Tablada3 5739', '22-93337511-3', '+5493513059163', 'c3@gmail.com', SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time', 'CODIGO_C3')
         , ('Auto City', 'La Tablada4 5739', '23-93337511-4', '+5493513059164', 'c4@gmail.com', SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time', 'CODIGO_C4')
         , ('Naum', 'La Tablada5 5739', '24-93337511-5', '+5493513059165', 'c5@gmail.com', SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time', 'CODIGO_C5')

    INSERT INTO concesionaria_x_config_tecnologica(concesionaria_id, config_tecnologica)
    VALUES (1, 'AXIS')
         , (2, 'REST')
         , (3, 'REST')
         , (4, 'CXF')
         , (5, 'CXF')

    INSERT INTO concesionaria_config_params (concesionaria_id, config_tecno, config_param, value)
    VALUES (1, 'AXIS', 'endpointUrl', 'http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/')
         , (1, 'AXIS', 'targetNameSpace', 'http://ws.ConcesionariaAxisOne/')
         , (2, 'REST', 'url', 'http://localhost:8001/concesionaria_rest_one/concesionariaRestOne')
         , (3, 'REST', 'url', 'http://localhost:8003/concesionaria_rest_two/concesionariaRestTwo')
         , (4, 'CXF', 'wsdlUrl', 'http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl')
         , (5, 'CXF', 'wsdlUrl', 'http://localhost:8004/concesionaria_cxf_two/services/concesionaria_cxf_two?wsdl')

END
GO