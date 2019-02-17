CREATE PROCEDURE get_concesionaria_by_cuit ( @cuit VARCHAR(50) )  AS
SELECT *
FROM concesionaria
WHERE cuit = @cuit;