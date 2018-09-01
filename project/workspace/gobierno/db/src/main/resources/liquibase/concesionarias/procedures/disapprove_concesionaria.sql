CREATE PROCEDURE disapprove_concesionaria(
    @id BIGINT
) AS
UPDATE concesionaria
SET fecha_alta = GETDATE()
    , codigo = NULL
WHERE id = @id;