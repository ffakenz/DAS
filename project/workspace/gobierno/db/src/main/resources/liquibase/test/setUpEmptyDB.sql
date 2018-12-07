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
END
GO