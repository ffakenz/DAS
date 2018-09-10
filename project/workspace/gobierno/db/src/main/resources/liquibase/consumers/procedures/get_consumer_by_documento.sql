CREATE PROCEDURE get_consumer_by_documento (
      @documento BIGINT
) AS
SELECT *
FROM consumers
WHERE documento = @documento;