CREATE PROCEDURE approve_concesionaria(
    @id BIGINT
    , @codigo VARCHAR(50)
) AS
UPDATE concesionaria
SET fecha_alta = GETDATE()
    , codigo = @codigo
WHERE id = @id
    AND codigo IS NULL;