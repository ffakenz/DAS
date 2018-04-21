CREATE PROCEDURE log_concesionarias(@nombre VARCHAR(100), @config VARCHAR(100)) AS
INSERT INTO concesionaria(nombre, config)
VALUES (@nombre, @config);