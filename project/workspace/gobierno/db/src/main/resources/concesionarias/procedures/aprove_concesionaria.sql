CREATE PROCEDURE aprove_concesionaria(@id BIGINT, @fecha_alta DATETIME, @codigo VARCHAR(10)) AS
UPDATE concesionaria
SET fecha_alta = @fecha_alta
    , codigo = @codigo
WHERE id = @id;