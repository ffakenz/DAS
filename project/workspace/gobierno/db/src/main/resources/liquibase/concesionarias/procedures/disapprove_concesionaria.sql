CREATE PROCEDURE disapprove_concesionaria(
    @id BIGINT
) AS
UPDATE concesionaria
SET codigo = NULL
WHERE id = @id;