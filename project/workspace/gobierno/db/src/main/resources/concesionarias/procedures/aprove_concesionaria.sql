CREATE PROCEDURE aprove_concesionaria(@cuit VARCHAR(50), @fecha_alta DATETIME, @codigo VARCHAR(50)) AS
UPDATE concesionaria
SET fecha_alta = @fecha_alta
    , codigo = @codigo
WHERE cuit = @cuit;