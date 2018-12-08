CREATE PROCEDURE get_last_consumo (
    @id_concesionaria BIGINT
) AS
SELECT TOP 1 *
FROM consumo
WHERE id_concesionaria = @id_concesionaria
ORDER BY id DESC;
