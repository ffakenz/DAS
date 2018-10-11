CREATE PROCEDURE get_concesionaria_by_id ( @id BIGINT )  AS
SELECT *
FROM concesionaria
WHERE id = @id;