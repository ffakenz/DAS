CREATE PROCEDURE get_usuario_by_documento(@documento BIGINT)  AS
SELECT *
FROM usuario
WHERE documento = @documento;