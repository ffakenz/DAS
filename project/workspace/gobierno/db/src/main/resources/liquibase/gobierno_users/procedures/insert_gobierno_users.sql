CREATE PROCEDURE insert_gobierno_users (
    @documento BIGINT
    , @nombre VARCHAR(100)
    , @apellido VARCHAR(100)
    , @nro_telefono VARCHAR(20)
    , @email VARCHAR(50)

) AS
INSERT INTO gobierno_users(documento, nombre, apellido, nro_telefono, email)
VALUES (@documento, @nombre, @apellido, @nro_telefono, @email);