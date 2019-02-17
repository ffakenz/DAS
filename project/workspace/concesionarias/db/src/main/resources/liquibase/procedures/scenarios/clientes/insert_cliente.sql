CREATE PROCEDURE insert_cliente (
    @documento       BIGINT
    , @nombre        VARCHAR(100)
    , @apellido      VARCHAR(100)
    , @nro_telefono  VARCHAR(20)
    , @email         VARCHAR(50)
    , @fecha_de_alta DATETIME
)
AS
INSERT INTO dbo.clientes (documento, nombre, apellido, nro_telefono, email, fecha_de_alta)
VALUES (@documento, @nombre, @apellido, @nro_telefono, @email, @fecha_de_alta)