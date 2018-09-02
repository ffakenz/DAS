CREATE PROCEDURE log_concesionaria (
    @nombre VARCHAR(100)
    , @direccion VARCHAR(100)
    , @cuit VARCHAR(50)
    , @tel VARCHAR(50)
    , @email VARCHAR(50)
) AS
INSERT INTO concesionaria(nombre, direccion, cuit, tel, email)
VALUES (@nombre, @direccion, @cuit, @tel, @email);