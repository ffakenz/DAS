-- USED TO SUPPORT ConsumerJobIntegration
CREATE PROCEDURE setUpDBConsumoIntegration AS
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
      ,('pendiente_consumo')
      ,('pendiente_cancelacion')
      ,('pendiente_seleccion_ganador')
      ,('pendiente_notificacion_ganador')
      ,('pendiente_notificacion_concesionarias')
      ,('completado')
      ,('fallado')

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
END
GO