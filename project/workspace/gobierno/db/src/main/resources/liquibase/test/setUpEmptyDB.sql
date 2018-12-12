-- USED TO SUPPORT ConsumerJobSpec
CREATE PROCEDURE setUpEmptyDB AS
BEGIN

    INSERT INTO tipos_vehiculo(nombre)
    VALUES('taxi')
        ,('particular')
        ,('comercial')
        ,('camion')
        ,('utilitario')

    INSERT INTO vehiculos(tipo, nombre, precio, marca, modelo, color)
    VALUES ('taxi', 'Corsa', 10000, 'chevrolet', 'v1','c1' )
        , ('particular', 'Gol', 10000, 'volkswagen', 'v3','c2' )
        ,('particular', 'Clio', 10000, 'renault', 'v1','c1' )
        , ('comercial', '208', 10000, 'peugeot', 'v1','c4' )
        , ('comercial', 'Focus', 10000, 'ford', 'v5','c3' )
        , ('utilitario', 'Fiorino', 10000, 'fiat', 'v1','c1' )
        , ('utilitario', 'Saveiro', 10000, 'volkswagen', 'v2','c1' )

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
    VALUES ('inicial')
            , ('en_proceso')
            , ('pagado')
            , ('cancelado')

    INSERT INTO estado_sorteo(nombre)
    VALUES ('nuevo')
            , ('pendiente'),('completado')

    INSERT INTO estado_participante(nombre)
    VALUES ('participante')
            , ('ganador')
            , ('cancelado')

    INSERT INTO estado_consumo(estado)
    VALUES ('success')
            , ('failure')

    INSERT INTO tipo_consumo_result(tipo)
    VALUES ('success')
            , ('failure')

    INSERT INTO concesionaria(nombre, direccion, cuit, tel, email)
    VALUES ('C1', 'La Tablada 5739', '21-93337511-1', '+5493513059161', 'c1@gmail.com')

    INSERT INTO consumers(documento, nombre, apellido, nro_telefono, email)
    VALUES
        (1, 'Carlos', 'Perez', '35156345678', 'carliperezozo@mail.com')

    INSERT INTO estado_cuentas (
        concesionaria, nro_plan_concesionaria, dni_consumer, vehiculo, fecha_alta_concesionaria, estado
    )
    VALUES (1, 1001, 111, 1, '2018-01-01 21:58:01', 'en_proceso')

   INSERT INTO cuotas (
       nro_cuota,
       estado_cuenta_id,
       fecha_alta_concesionaria,
       fecha_vencimiento,
       monto,
       fecha_pago
   )
   VALUES (1, 1, '2018-01-01 21:58:01', '2018-02-01 21:58:01', 10000, '2018-01-27 21:58:01')
          , (2, 1, '2018-02-01 21:58:01', '2018-03-01 21:58:01', null, null)
END
GO