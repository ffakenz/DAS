CREATE PROCEDURE insert_consumers (
    @documento BIGINT
    , @nombre VARCHAR(100)
    , @apellido VARCHAR(100)
    , @nro_telefono VARCHAR(20)
    , @email VARCHAR(50)
) AS
INSERT INTO consumers(documento, nombre, apellido, nro_telefono, email)
VALUES (@documento, @nombre, @apellido, @nro_telefono, @email);