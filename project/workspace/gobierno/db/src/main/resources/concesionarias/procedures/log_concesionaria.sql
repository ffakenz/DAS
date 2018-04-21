CREATE PROCEDURE log_concesionaria(@nombre VARCHAR(100), @config VARCHAR(100)) AS
INSERT INTO concesionaria(nombre, config)
VALUES (@nombre, @config);