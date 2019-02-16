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


    INSERT INTO tipo_estado_planes(nombre)
    VALUES ('inicial')
           , ('en_proceso')
           , ('pagado')
           , ('cancelado')

    INSERT INTO tipos_de_planes(nombre)
    VALUES ('GOB'),('NORMAL')
END
GO