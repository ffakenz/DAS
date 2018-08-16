CREATE PROCEDURE get_consumer_by_documento (
      @documento BIGINT
) AS
SELECT *
FROM gobierno_users
WHERE documento = @documento;