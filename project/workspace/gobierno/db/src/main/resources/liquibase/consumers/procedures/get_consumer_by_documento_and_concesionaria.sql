CREATE PROCEDURE get_consumer_by_documento_and_concesionaria (
      @documento BIGINT,
      @concesionaria BIGINT
) AS
SELECT *
FROM consumers
WHERE documento = @documento
  AND concesionaria = @concesionaria;