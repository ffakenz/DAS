-- USED TO SUPPORT ConsumerJobSpec
CREATE PROCEDURE setUpEmptyDB AS
BEGIN

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

    INSERT INTO consumers(documento, nombre, apellido, nro_telefono, email, concesionaria)
    VALUES
        (111, 'Carlos', 'Perez', '35156345678', 'carliperezozo@mail.com', 1)

    INSERT INTO estado_cuentas (
        concesionaria
        , nro_plan_concesionaria
        , dni_consumer
        , vehiculo
        , fecha_alta_concesionaria
        , estado
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